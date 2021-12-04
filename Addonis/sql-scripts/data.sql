
INSERT INTO addonis.roles (id, role_name) VALUES (1, 'Admin');
INSERT INTO addonis.roles (id, role_name) VALUES (2, 'User');

INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (1, 'IntellijIDE', 'intellij.png');
INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (2, 'Eclipse', 'eclipse.png');
INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (3, 'Visual Studio', 'studio.png');
INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (4, 'PyCharm', 'pycharm.png');

INSERT INTO addonis.tags (id, name) VALUES (1, 'Code');
INSERT INTO addonis.tags (id, name) VALUES (2, 'Databases');


INSERT INTO addonis.users (id, username, password, first_name, last_name, email, picture_url, blocked, role_id, phone_number, enabled)
VALUES (1, 'admin', 'pass', 'Admin', 'Adminov', 'admin@gmail.com', 'test_pic_url', 0, 1, '0898758267', false);

INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (15, 'Merge branch ''2.7.x''', 23, 530, '2021-12-01', '2021-12-01 17:00:03');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (23, 'Merge pull request #13 from HelloWorld521/dependabot/maven/seckill/com.fasterxml.jackson.core-jackson-databind-2.9.10.1

Bump jackson-databind from 2.5.4 to 2.9.10.1 in /seckill', 32, 12, '2019-11-13', '2021-11-30 18:28:21');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (24, 'Merge pull request #13 from HelloWorld521/dependabot/maven/seckill/com.fasterxml.jackson.core-jackson-databind-2.9.10.1

Bump jackson-databind from 2.5.4 to 2.9.10.1 in /seckill', 32, 13, '2019-11-13', '2021-12-01 17:00:06');



INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (10, 'name', 1, 1, 'test', 'https://github.com/spring-projects/spring-boot', 'test', 2, '2021-11-26', 0, 0, 15);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (15, 'name3', 2, 1, 'test', 'https://github.com/HelloWorld521/Java', 'test', 3, '2021-11-30', 1, 0, 24);


INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (2, 1, 10, 1);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (3, 1, 15, 4);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (4, 1, 10, 3);


INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (4, 15, 2);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (5, 15, 1);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (6, 10, 1);
