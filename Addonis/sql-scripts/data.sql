
INSERT INTO addonis.roles (id, role_name) VALUES (1, 'Admin');
INSERT INTO addonis.roles (id, role_name) VALUES (2, 'User');

INSERT INTO addonis.target_ide (id, name) VALUES (1, 'IntellijIDE');

INSERT INTO addonis.users (id, username, password, first_name, last_name, email, picture_url, blocked, role_id, phone_number, enabled)
VALUES (1, 'admin', 'pass', 'Admin', 'Adminov', 'admin@gmail.com', 'test_pic_url', 0, 1, '0898758267', false);