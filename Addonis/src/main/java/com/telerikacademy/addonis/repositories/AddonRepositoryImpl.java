package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.repositories.contracts.AddonRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.lang.String.format;

@Repository
public class AddonRepositoryImpl extends CRUDSQLRepository<Addon> implements AddonRepository {

    private static final int LIMIT = 4;

    @Autowired
    protected AddonRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Addon.class);
    }

    @Override
    public Addon findByOriginUrl(String originUrl) {
        String query = format("from %s where %s = :value", getClazz().getSimpleName(), "originUrl");
        try (Session session = getSessionFactory().openSession()) {
            return session
                    .createQuery(query, getClazz())
                    .setParameter("value", originUrl)
                    .uniqueResultOptional()
                    .orElseThrow(() -> new EntityNotFoundException("Addon", "originUrl", originUrl));
        }
    }

    @Override
    public List<Addon> getFeatured() {
        String query = format("from %s where %s = :value", getClazz().getSimpleName(), "featured");
        try (Session session = getSessionFactory().openSession()) {
            return session
                    .createQuery(query, getClazz())
                    .setParameter("value", true)
                    .list();
        }
    }

    @Override
    public List<Addon> getNewest() {
        String query = format("from %s where %s = :value order by %s desc", getClazz().getSimpleName(), "approved", "creationDate");
        try (Session session = getSessionFactory().openSession()) {
            return session
                    .createQuery(query, getClazz())
                    .setParameter("value", true)
                    .setMaxResults(LIMIT)
                    .list();
        }
    }

    @Override
    public List<Addon> getPopular() {
        String query = format("from %s where %s = :value order by %s desc", getClazz().getSimpleName(), "approved", "numberOfDownloads");
        try (Session session = getSessionFactory().openSession()) {
            return session
                    .createQuery(query, getClazz())
                    .setParameter("value", true)
                    .setMaxResults(LIMIT)
                    .list();
        }
    }

    @Override
    public Addon getByName(String name) {
        String query = format("from %s where %s = :value", getClazz().getSimpleName(), "name");
        try (Session session = getSessionFactory().openSession()) {
            return session
                    .createQuery(query, getClazz())
                    .setParameter("value", name)
                    .uniqueResultOptional()
                    .orElseThrow(() -> new EntityNotFoundException("Addon", "name", name));
        }
    }

    @Override
    public List<Addon> getByUser(int userId) {
        try (Session session = getSessionFactory().openSession()) {
            Query<Addon> query = session.createQuery("from Addon where creator.id = :userId", Addon.class);
            query.setParameter("userId",userId);
            return query.list();
        }
    }

    @Override
    public List<Addon> getPending() {
        String query = format("from %s where %s = :value", getClazz().getSimpleName(), "approved");
        try (Session session = getSessionFactory().openSession()) {
            return session
                    .createQuery(query, getClazz())
                    .setParameter("value", false)
                    .list();
        }
    }

    @Override
    public List<Addon> filter(Optional<String> name, Optional<Integer> targetIdeId, Optional<String> sort) {
        try (Session session = getSessionFactory().openSession()) {
            var queryString = new StringBuilder(" from Addon ");
            var filter = new ArrayList<String>();
            var params = new HashMap<String, Object>();

            name.ifPresent(value -> {
                filter.add("name like :name");
                params.put("name", "%"+name.get()+"%");
            });

            targetIdeId.ifPresent(value -> {
                filter.add("targetIde.id = :targetIdeId");
                params.put("targetIdeId", targetIdeId.get());
            });

            filter.add("approved = :value");
            params.put("value",true);

            if(!filter.isEmpty()) {
                queryString.append(" where ").append(String.join(" and ", filter));
            }
            sort.ifPresent(s -> queryString.append(generateAscDescString(sort.get())));
            Query<Addon> query = session.createQuery(queryString.toString(), Addon.class);
            query.setProperties(params);
            return query.list();
        }
    }


    public String generateAscDescString(String sort){
        var endQuery =new StringBuilder(" order by ");
        String[] strArray = sort.split("_");
        switch (strArray[0]){
            case "name":
                endQuery.append(" name ");
                break;
            case "download":
                endQuery.append(" numberOfDownloads ");
                break;
            case "uploadDate":
                endQuery.append(" creationDate ");
                break;
            case "lastCommitDate":
                endQuery.append(" repoInfo.lastCommitDate ");
                break;
        }
        if(strArray.length > 1 && strArray[1].equals("desc")){
            endQuery.append(" desc ");
        }
        return endQuery.toString();
    }


}


