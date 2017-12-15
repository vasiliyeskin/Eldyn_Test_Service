DELETE FROM user_roles;
DELETE FROM UserAnswer;
DELETE FROM answerAndQuestions;
DELETE FROM testAndQuestions;
DELETE FROM test;
DELETE FROM question;
DELETE FROM answer;
DELETE FROM users;
ALTER SEQUENCE global_seq           RESTART WITH 100000;
ALTER SEQUENCE global_seqTest       RESTART WITH 1;
ALTER SEQUENCE global_seqQuestion   RESTART WITH 1;
ALTER SEQUENCE global_seqAnswer     RESTART WITH 1;
ALTER SEQUENCE global_seqTQ         RESTART WITH 1;
ALTER SEQUENCE global_seqATQ        RESTART WITH 1;
ALTER SEQUENCE global_seqUserAnswer RESTART WITH 1;




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



/*select
  test0_.id as id1_3_0_,
  test0_.creationdatetime as creation2_3_0_,
  test0_.creatorId as creatorI5_3_0_,
  test0_.image as image3_3_0_,
  test0_.text as text4_3_0_,
  user1_.id as id1_7_1_,
  user1_.active as active2_7_1_,
  user1_.email as email3_7_1_,
  user1_.firstname as firstnam4_7_1_,
  user1_.lastname as lastname5_7_1_,
  user1_.password as password6_7_1_,
  user1_.registered as register7_7_1_,
  roles2_.user_id as user_id1_5_2_,
  roles2_.role as role2_5_2_,
  questionsl3_.testId as testId1_4_3_,
  question4_.id as question2_4_3_,
  question4_.id as id1_2_4_,
  question4_.creationdatetime as creation2_2_4_,
  question4_.creatorId as creatorI5_2_4_,
  question4_.image as image3_2_4_,
  question4_.text as text4_2_4_,
  answerslis5_.questionID as question7_1_5_,
  teacherans6_.id as answerID5_1_5_,
  teacherans6_.id as id1_1_6_,
  teacherans6_.answerID as answerID5_1_6_,
  teacherans6_.creationdatetime as creation2_1_6_,
  teacherans6_.creatorId as creatorI6_1_6_,
  teacherans6_.isRight as isRight3_1_6_,
  teacherans6_.testAnswer as testAnsw4_1_6_,
  user7_.id as id1_7_7_,
  user7_.active as active2_7_7_,
  user7_.email as email3_7_7_,
  user7_.firstname as firstnam4_7_7_,
  user7_.lastname as lastname5_7_7_,
  user7_.password as password6_7_7_,
  user7_.registered as register7_7_7_,
  user8_.id as id1_7_8_,
  user8_.active as active2_7_8_,
  user8_.email as email3_7_8_,
  user8_.firstname as firstnam4_7_8_,
  user8_.lastname as lastname5_7_8_,
  user8_.password as password6_7_8_,
  user8_.registered as register7_7_8_
from
  test test0_
  left outer join
  users user1_
    on test0_.creatorId=user1_.id
  left outer join
  user_roles roles2_
    on user1_.id=roles2_.user_id
  left outer join
  testAndQuestions questionsl3_
    on test0_.id=questionsl3_.testId
  left outer join
  question question4_
    on questionsl3_.questionID=question4_.id
  left outer join
  answerAndQuestions answerslis5_
    on question4_.id=answerslis5_.questionID
  left outer join
  answerAndQuestions teacherans6_
    on answerslis5_.answerID=teacherans6_.id
  left outer join
  users user7_
    on teacherans6_.creatorId=user7_.id
  left outer join
  users user8_
    on question4_.creatorId=user8_.id
where
  test0_.id=1*/

