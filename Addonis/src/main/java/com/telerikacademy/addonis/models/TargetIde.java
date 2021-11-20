package com.telerikacademy.addonis.models;

import javax.persistence.*;

@Table(name = "target_ide")
@Entity
public class TargetIde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}