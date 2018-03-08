DELETE FROM user_roles;
DELETE FROM UserAnswers;
DELETE FROM UserQuestions;
DELETE FROM UserTests;
DELETE FROM answerAndQuestions;
DELETE FROM testAndQuestions;
DELETE FROM test;
DELETE FROM question;
DELETE FROM answer;
DELETE FROM users;
ALTER SEQUENCE global_seq              RESTART WITH 100000;
ALTER SEQUENCE global_seqTest          RESTART WITH 1;
ALTER SEQUENCE global_seqQuestion      RESTART WITH 1;
ALTER SEQUENCE global_seqAnswer        RESTART WITH 1;
ALTER SEQUENCE global_seqTQ            RESTART WITH 1;
ALTER SEQUENCE global_seqATQ           RESTART WITH 1;
ALTER SEQUENCE global_seqUserAnswer    RESTART WITH 1;
ALTER SEQUENCE global_seqUserQuestion  RESTART WITH 1;
ALTER SEQUENCE global_seqUserTest      RESTART WITH 1;




INSERT INTO users (firstname, lastname, email, password)
VALUES ('Student', 'Smith', 'user@yandex.ru', 'password');
-- PasswordUtil.encode("password");
-- INSERT INTO users (firstname, lastname, email, password)
-- VALUES ('Student', 'Smith', 'user@yandex.ru', '$2a$10$i09JKq1iXzAYl2o6yLvfBuJYRXhypm9P06voTexk0X6N46qTNt606');

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

INSERT INTO test (text, image, creationdatetime, creatorId) VALUES
  ('algebra', NULL, now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ('geometry', NULL, now(), (SELECT id FROM users WHERE email ='teacher@yandex.ru'));


INSERT INTO question (text, image, creationdatetime, creatorId) VALUES
  ('1+2', NULL, now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ('1+3', NULL, now(), (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ('Where is triangular?', NULL, now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ('Where is circle?', NULL, now(), (SELECT id FROM users WHERE email ='teacher@yandex.ru'));


INSERT INTO answer (text, image, creationdatetime, creatorId) VALUES
  ('1', NULL, now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ('2', NULL, now(), (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ('3', NULL, now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ('4', NULL, now(), (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ('here', NULL, now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ('there', NULL, now(), (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ('0', NULL, now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru'));

INSERT INTO testandquestions (testId, questionID, creationdatetime, creatorId) VALUES
  ((SELECT id FROM test WHERE text = 'algebra'), (SELECT id FROM question WHERE text = '1+2'), now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ((SELECT id FROM test WHERE text = 'algebra'), (SELECT id FROM question WHERE text = '1+3'), now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ((SELECT id FROM test WHERE text = 'geometry'), (SELECT id FROM question WHERE text = 'Where is triangular?'), now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  ((SELECT id FROM test WHERE text = 'geometry'), (SELECT id FROM question WHERE text = 'Where is circle?'), now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru'));

INSERT INTO answerAndQuestions (questionid, answerID, isRight, testAnswer, creationdatetime, creatorId) VALUES
  (1, 1, TRUE , NULL ,now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  (1, 2, FALSE , NULL ,now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  (1, 7, TRUE , NULL ,now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  (2, 1, TRUE , NULL ,now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  (3, 3, FALSE , NULL ,now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  (2, 2, TRUE , NULL ,now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  (3, 4, FALSE , NULL ,now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  (4, 3, TRUE , NULL ,now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  (4, 4, FALSE , NULL ,now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru'));


INSERT INTO usertests (testID, creationdatetime, userId) VALUES
  (1, now(), (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  (1, now(), (SELECT id FROM users WHERE email ='user@yandex.ru'));

INSERT INTO UserQuestions (usertestID, questionTestID, creationdatetime, userId) VALUES
  (1, 1, now(), (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  (2, 3, now(), (SELECT id FROM users WHERE email ='user@yandex.ru'));

INSERT INTO UserAnswers (userquestionID, answerID, isRight, testAnswer, creationdatetime, userId) VALUES
  (1, 1, TRUE, '', now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru')),
  (2, 7, TRUE, '', now(),  (SELECT id FROM users WHERE email ='teacher@yandex.ru'));



DELETE FROM students;
ALTER SEQUENCE global_seq_student      RESTART WITH 1;
INSERT INTO students (lastname, firstname, midlename, course, active, email, phone)
VALUES ('Порфирьев','Павел','Сергеевич', 3, TRUE, 'test@test.ru', 'test'),
 ('Смирнов','Дмитрий','Валерьевич', 3, TRUE, 'test@test.ru', 'test'),
 ('Шурыгин','Александр','Евгеньевич', 3, TRUE, 'test@test.ru', 'test');