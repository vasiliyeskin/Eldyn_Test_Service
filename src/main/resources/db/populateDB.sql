DELETE FROM user_roles;
DELETE FROM UserAnswer;
DELETE FROM answerOfTestAndQuestions;
DELETE FROM testAndQuestions;
DELETE FROM test;
DELETE FROM question;
DELETE FROM answer;
DELETE FROM users;
ALTER SEQUENCE global_seq
RESTART WITH 100000;


INSERT INTO users (firstname, lastname, email, password)
VALUES ('User', 'Smith', 'user@yandex.ru', 'password');
-- PasswordUtil.encode("password");
-- INSERT INTO users (firstname, lastname, email, password)
-- VALUES ('User', 'Smith', 'user@yandex.ru', '$2a$10$i09JKq1iXzAYl2o6yLvfBuJYRXhypm9P06voTexk0X6N46qTNt606');

INSERT INTO users (firstname, lastname, email, password)
VALUES ('T1000', 'T2', 'teacher@yandex.ru', 'password');

INSERT INTO users (firstname, lastname, email, password)
VALUES ('Admin', 'Smith', 'admin@gmail.com', 'admin');
-- PasswordUtil.encode("admin");
-- INSERT INTO users (firstname, lastname, email, password)
-- VALUES ('Admin', 'Smith', 'admin@gmail.com', '$2a$10$M7C82phiJOuaQSS/ygZVtebkxmsUWEN9ZdTVstJsQcEaRqf2bAz0C');


INSERT INTO users (firstname, lastname, email, password)
VALUES ('User2', 'Smith', 'user2@yandex.ru', '0'),
  ('User3', 'Smith', 'user3@yandex.ru', '0'),
  ('User4', 'Smith', 'user4@yandex.ru', '0');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', (SELECT id
                 FROM USERS
                 WHERE email = 'user@yandex.ru')),
  ('ROLE_TEACHER', (SELECT id
                 FROM USERS
                 WHERE email = 'teacher@yandex.ru')),
  ('ROLE_ADMIN', (SELECT id
                  FROM USERS
                  WHERE email = 'admin@gmail.com')),
  ('ROLE_USER', (SELECT id
                 FROM USERS
                 WHERE email = 'admin@gmail.com')),
  ('ROLE_USER', (SELECT id
                 FROM USERS
                 WHERE email = 'user2@yandex.ru')),
  ('ROLE_USER', (SELECT id
                 FROM USERS
                 WHERE email = 'user3@yandex.ru')),
  ('ROLE_USER', (SELECT id
                 FROM USERS
                 WHERE email = 'user4@yandex.ru'));


