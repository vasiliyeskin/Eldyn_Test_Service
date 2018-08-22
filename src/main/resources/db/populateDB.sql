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
DELETE FROM students_practice;
DELETE FROM students_scientificadviser;
DELETE FROM adviser_position;
DELETE FROM adviser_organization;
DELETE FROM students;
DELETE FROM scientific_adviser;
DELETE FROM curator;
DELETE FROM position_in_the_organization;
DELETE FROM organization;
DELETE FROM practice;
DELETE FROM training_direction;
DELETE FROM students_trainingdirection;

ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE global_seqTest RESTART WITH 1;
ALTER SEQUENCE global_seqQuestion RESTART WITH 1;
ALTER SEQUENCE global_seqAnswer RESTART WITH 1;
ALTER SEQUENCE global_seqTQ RESTART WITH 1;
ALTER SEQUENCE global_seqATQ RESTART WITH 1;
ALTER SEQUENCE global_seqUserAnswer RESTART WITH 1;
ALTER SEQUENCE global_seqUserQuestion RESTART WITH 1;
ALTER SEQUENCE global_seqUserTest RESTART WITH 1;


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
  ('algebra', NULL, now(), (SELECT id
                            FROM users
                            WHERE email = 'teacher@yandex.ru')),
  ('geometry', NULL, now(), (SELECT id
                             FROM users
                             WHERE email = 'teacher@yandex.ru'));


INSERT INTO question (text, image, creationdatetime, creatorId) VALUES
  ('1+2', NULL, now(), (SELECT id
                        FROM users
                        WHERE email = 'teacher@yandex.ru')),
  ('1+3', NULL, now(), (SELECT id
                        FROM users
                        WHERE email = 'teacher@yandex.ru')),
  ('Where is triangular?', NULL, now(), (SELECT id
                                         FROM users
                                         WHERE email = 'teacher@yandex.ru')),
  ('Where is circle?', NULL, now(), (SELECT id
                                     FROM users
                                     WHERE email = 'teacher@yandex.ru'));


INSERT INTO answer (text, image, creationdatetime, creatorId) VALUES
  ('1', NULL, now(), (SELECT id
                      FROM users
                      WHERE email = 'teacher@yandex.ru')),
  ('2', NULL, now(), (SELECT id
                      FROM users
                      WHERE email = 'teacher@yandex.ru')),
  ('3', NULL, now(), (SELECT id
                      FROM users
                      WHERE email = 'teacher@yandex.ru')),
  ('4', NULL, now(), (SELECT id
                      FROM users
                      WHERE email = 'teacher@yandex.ru')),
  ('here', NULL, now(), (SELECT id
                         FROM users
                         WHERE email = 'teacher@yandex.ru')),
  ('there', NULL, now(), (SELECT id
                          FROM users
                          WHERE email = 'teacher@yandex.ru')),
  ('0', NULL, now(), (SELECT id
                      FROM users
                      WHERE email = 'teacher@yandex.ru'));

INSERT INTO testandquestions (testId, questionID, creationdatetime, creatorId) VALUES
  ((SELECT id
    FROM test
    WHERE text = 'algebra'), (SELECT id
                              FROM question
                              WHERE text = '1+2'), now(), (SELECT id
                                                           FROM users
                                                           WHERE email = 'teacher@yandex.ru')),
  ((SELECT id
    FROM test
    WHERE text = 'algebra'), (SELECT id
                              FROM question
                              WHERE text = '1+3'), now(), (SELECT id
                                                           FROM users
                                                           WHERE email = 'teacher@yandex.ru')),
  ((SELECT id
    FROM test
    WHERE text = 'geometry'), (SELECT id
                               FROM question
                               WHERE text = 'Where is triangular?'), now(), (SELECT id
                                                                             FROM users
                                                                             WHERE email = 'teacher@yandex.ru')),
  ((SELECT id
    FROM test
    WHERE text = 'geometry'), (SELECT id
                               FROM question
                               WHERE text = 'Where is circle?'), now(), (SELECT id
                                                                         FROM users
                                                                         WHERE email = 'teacher@yandex.ru'));

INSERT INTO answerAndQuestions (questionid, answerID, isRight, testAnswer, creationdatetime, creatorId) VALUES
  (1, 1, TRUE, NULL, now(), (SELECT id
                             FROM users
                             WHERE email = 'teacher@yandex.ru')),
  (1, 2, FALSE, NULL, now(), (SELECT id
                              FROM users
                              WHERE email = 'teacher@yandex.ru')),
  (1, 7, TRUE, NULL, now(), (SELECT id
                             FROM users
                             WHERE email = 'teacher@yandex.ru')),
  (2, 1, TRUE, NULL, now(), (SELECT id
                             FROM users
                             WHERE email = 'teacher@yandex.ru')),
  (3, 3, FALSE, NULL, now(), (SELECT id
                              FROM users
                              WHERE email = 'teacher@yandex.ru')),
  (2, 2, TRUE, NULL, now(), (SELECT id
                             FROM users
                             WHERE email = 'teacher@yandex.ru')),
  (3, 4, FALSE, NULL, now(), (SELECT id
                              FROM users
                              WHERE email = 'teacher@yandex.ru')),
  (4, 3, TRUE, NULL, now(), (SELECT id
                             FROM users
                             WHERE email = 'teacher@yandex.ru')),
  (4, 4, FALSE, NULL, now(), (SELECT id
                              FROM users
                              WHERE email = 'teacher@yandex.ru'));


INSERT INTO usertests (testID, creationdatetime, userId) VALUES
  (1, now(), (SELECT id
              FROM users
              WHERE email = 'teacher@yandex.ru')),
  (1, now(), (SELECT id
              FROM users
              WHERE email = 'user@yandex.ru'));

INSERT INTO UserQuestions (usertestID, questionTestID, creationdatetime, userId) VALUES
  (1, 1, now(), (SELECT id
                 FROM users
                 WHERE email = 'teacher@yandex.ru')),
  (2, 3, now(), (SELECT id
                 FROM users
                 WHERE email = 'user@yandex.ru'));

INSERT INTO UserAnswers (userquestionID, answerID, isRight, testAnswer, creationdatetime, userId) VALUES
  (1, 1, TRUE, '', now(), (SELECT id
                           FROM users
                           WHERE email = 'teacher@yandex.ru')),
  (2, 7, TRUE, '', now(), (SELECT id
                           FROM users
                           WHERE email = 'teacher@yandex.ru'));

-- students and curator

DELETE FROM students;
ALTER SEQUENCE global_seq_student RESTART WITH 1;
INSERT INTO students (lastname, firstname, midlename, course, active, email, phone)
VALUES ('Порфирьев', 'Павел', 'Сергеевич', 3, TRUE, 'test@test.ru', 'test'),
  ('Смирнов', 'Дмитрий', 'Валерьевич', 3, TRUE, 'test@test.ru', 'test'),
  ('Шурыгин', 'Александр', 'Евгеньевич', 3, TRUE, 'test@test.ru', 'test'),
  ('Емельянов', 'Илья', 'Александрович', 3, TRUE, 'test@test.ru', 'test'),
  ('Лобанова', 'Анастасия', 'Александровна', 3, TRUE, 'test@test.ru', 'test'),
  ('Опачанов', 'Сергей', 'Геннадьевич', 3, TRUE, 'test@test.ru', 'test'),
  ('Степаненков', 'Виктор', 'Николаевич', 3, TRUE, 'test@test.ru', 'test'),
  ('Гапонов', 'Юлий', 'Михайлович', 3, TRUE, 'test@test.ru', 'test'),
  ('Жданов', 'Николай', 'Кириллович', 3, TRUE, 'test@test.ru', 'test'),
  ('Пухов', 'Дмитрий', 'Денисович', 3, TRUE, 'test@test.ru', 'test'),
  ('Федяков', 'Дмитрий', 'Вадимович', 3, TRUE, 'test@test.ru', 'test');


DELETE FROM scientific_adviser;
ALTER SEQUENCE global_seq_sa RESTART WITH 1;
INSERT INTO scientific_adviser (lastname, firstname, middlename, email, phone, iscurator)
VALUES
  ('Гущин', 'Михаил', 'Евгеньевич', '', '', false),
  ('Волков', 'Петр', 'Витальевич', '', '', false),
  ('Умнов', 'Алексей', 'Львович', '', '', true),
  ('Кирсанов', 'Алексей', 'Владимирович', '', '', false),
  ('Зиновьев', 'Андрей', 'Петрович', '', '', false),
  ('Еськин', 'Василий', 'Алексеевич', '', '', true);


DELETE FROM position_in_the_organization;
ALTER SEQUENCE global_seq_pio RESTART WITH 1;
INSERT INTO position_in_the_organization (positionio)
VALUES
  ('научный сотрудник '),
  ('доцент');


DELETE FROM organization;
ALTER SEQUENCE global_seq_o RESTART WITH 1;
INSERT INTO organization (name, namegenitive, shortname)
VALUES
  ('Институт прикладной физики Российской Академии наук', 'Института прикладной физики Российской Академии наук',
   'ИПФ РАН'),
  ('кафедра электродинамики ННГУ ', 'кафедры электродинамики ННГУ ', 'ННГУ'),
  ('Институт физики микроструктур Российской Академии наук ',
   'Института физики микроструктур Российской Академии наук ', 'ИФМ РАН');


SET datestyle = dmy;
DELETE FROM practice;
ALTER SEQUENCE global_seq_practice RESTART WITH 1;
INSERT INTO practice (name, nameDirection, nameRod, startDate, enddate)
VALUES
  ('УЧЕБНАЯ ПРАКТИКА; ПРАКТИКА ПО ПОЛУЧЕНИЮ ПЕРВИЧНЫХ ПРОФЕССИОНАЛЬНЫХ УМЕНИЙ И НАВЫКОВ, В ТОМ ЧИСЛЕ ПЕРВИЧНЫХ УМЕНИЙ И НАВЫКОВ НАУЧНО-ИССЛЕДОВАТЕЛЬСКОЙ ДЕЯТЕЛЬНОСТИ',
    'УЧЕБНУЮ ПРАКТИКУ; ПРАКТИКУ ПО ПОЛУЧЕНИЮ ПЕРВИЧНЫХ ПРОФЕССИОНАЛЬНЫХ УМЕНИЙ И НАВЫКОВ, В ТОМ ЧИСЛЕ ПЕРВИЧНЫХ УМЕНИЙ И НАВЫКОВ НАУЧНО-ИССЛЕДОВАТЕЛЬСКОЙ ДЕЯТЕЛЬНОСТИ',
   'УЧЕБНОЙ ПРАКТИКИ; ПРАКТИКИ ПО ПОЛУЧЕНИЮ ПЕРВИЧНЫХ ПРОФЕССИОНАЛЬНЫХ УМЕНИЙ И НАВЫКОВ, В ТОМ ЧИСЛЕ ПЕРВИЧНЫХ УМЕНИЙ И НАВЫКОВ НАУЧНО-ИССЛЕДОВАТЕЛЬСКОЙ ДЕЯТЕЛЬНОСТИ',
    '02.02.2018', '31.05.2018'),
  ('НАУЧНО-ИССЛЕДОВАТЕЛЬСКАЯ ПРАКТИКА', 'НАУЧНО-ИССЛЕДОВАТЕЛЬСКУЮ ПРАКТИКУ', 'НАУЧНО-ИССЛЕДОВАТЕЛЬСКОЙ ПРАКТИКИ','02.02.2018', '03.05.2018'),
  ('ПРЕДДИПЛОМНАЯ ПРАКТИКА', 'ПРЕДДИПЛОМНУЮ ПРАКТИКУ', 'ПРЕДДИПЛОМНОЙ ПРАКТИКИ','02.02.2018', '03.05.2018');

INSERT INTO students_practice (student_id, practice_id) VALUES
  ((SELECT id
    FROM students
    WHERE lastname = 'Порфирьев'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Смирнов'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Шурыгин'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Емельянов'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Лобанова'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Опачанов'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Степаненков'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Гапонов'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Жданов'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Пухов'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Федяков'), 1);


DELETE FROM training_direction;
ALTER SEQUENCE global_seq_td RESTART WITH 1;
INSERT INTO training_direction (name, shortname)
VALUES
  ('Фундаментальная информатика и информационные технологии', 'ФИИТ'),
  ('Информационная безопасность телекоммуникационных систем', 'ИБТС'),
  ('Радиофизика', 'Радиофизика');


INSERT INTO students_trainingdirection (student_id, td_id) VALUES
  ((SELECT id
    FROM students
    WHERE lastname = 'Порфирьев'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Смирнов'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Шурыгин'), 1),
  ((SELECT id
    FROM students
    WHERE lastname = 'Емельянов'), 3),
  ((SELECT id
    FROM students
    WHERE lastname = 'Лобанова'), 3),
  ((SELECT id
    FROM students
    WHERE lastname = 'Опачанов'), 3),
  ((SELECT id
    FROM students
    WHERE lastname = 'Степаненков'), 3),
  ((SELECT id
    FROM students
    WHERE lastname = 'Гапонов'), 2),
  ((SELECT id
    FROM students
    WHERE lastname = 'Жданов'), 2),
  ((SELECT id
    FROM students
    WHERE lastname = 'Пухов'), 2),
  ((SELECT id
    FROM students
    WHERE lastname = 'Федяков'), 2);


INSERT INTO students_scientificadviser (student_id, adiver_id) VALUES
  ((SELECT id
    FROM students
    WHERE lastname = 'Порфирьев'), (SELECT id
                                    FROM scientific_adviser
                                    WHERE lastname = 'Кирсанов')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Смирнов'), (SELECT id
                                  FROM scientific_adviser
                                  WHERE lastname = 'Кирсанов')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Шурыгин'), (SELECT id
                                  FROM scientific_adviser
                                  WHERE lastname = 'Зиновьев')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Емельянов'), (SELECT id
                                    FROM scientific_adviser
                                    WHERE lastname = 'Гущин')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Лобанова'), (SELECT id
                                   FROM scientific_adviser
                                   WHERE lastname = 'Волков')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Опачанов'), (SELECT id
                                   FROM scientific_adviser
                                   WHERE lastname = 'Гущин')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Степаненков'), (SELECT id
                                      FROM scientific_adviser
                                      WHERE lastname = 'Волков')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Гапонов'), (SELECT id
                                  FROM scientific_adviser
                                  WHERE lastname = 'Умнов')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Жданов'), (SELECT id
                                 FROM scientific_adviser
                                 WHERE lastname = 'Умнов')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Пухов'), (SELECT id
                                FROM scientific_adviser
                                WHERE lastname = 'Умнов')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Федяков'), (SELECT id
                                  FROM scientific_adviser
                                  WHERE lastname = 'Умнов'));

INSERT INTO curator (student_id, curator_id) VALUES
  ((SELECT id
    FROM students
    WHERE lastname = 'Порфирьев'), (SELECT id
                                    FROM scientific_adviser
                                    WHERE lastname = 'Еськин')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Смирнов'), (SELECT id
                                  FROM scientific_adviser
                                  WHERE lastname = 'Еськин')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Шурыгин'), (SELECT id
                                  FROM scientific_adviser
                                  WHERE lastname = 'Еськин')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Емельянов'), (SELECT id
                                    FROM scientific_adviser
                                    WHERE lastname = 'Еськин')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Лобанова'), (SELECT id
                                   FROM scientific_adviser
                                   WHERE lastname = 'Еськин')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Опачанов'), (SELECT id
                                   FROM scientific_adviser
                                   WHERE lastname = 'Еськин')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Степаненков'), (SELECT id
                                      FROM scientific_adviser
                                      WHERE lastname = 'Еськин')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Гапонов'), (SELECT id
                                  FROM scientific_adviser
                                  WHERE lastname = 'Еськин')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Жданов'), (SELECT id
                                 FROM scientific_adviser
                                 WHERE lastname = 'Еськин')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Пухов'), (SELECT id
                                FROM scientific_adviser
                                WHERE lastname = 'Еськин')),
  ((SELECT id
    FROM students
    WHERE lastname = 'Федяков'), (SELECT id
                                  FROM scientific_adviser
                                  WHERE lastname = 'Еськин'));


INSERT INTO adviser_position (adiver_id, position_id) VALUES
  ((SELECT id
    FROM scientific_adviser
    WHERE lastname = 'Кирсанов'), 1),
  ((SELECT id
    FROM scientific_adviser
    WHERE lastname = 'Зиновьев'), 1),
  ((SELECT id
    FROM scientific_adviser
    WHERE lastname = 'Гущин'), 1),
  ((SELECT id
    FROM scientific_adviser
    WHERE lastname = 'Волков'), 1),
  ((SELECT id
    FROM scientific_adviser
    WHERE lastname = 'Умнов'), 2),
  ((SELECT id
    FROM scientific_adviser
    WHERE lastname = 'Еськин'), 2);

INSERT INTO adviser_organization (adiver_id, organization_id) VALUES
  ((SELECT id
    FROM scientific_adviser
    WHERE lastname = 'Кирсанов'), 1),
  ((SELECT id
    FROM scientific_adviser
    WHERE lastname = 'Зиновьев'), 1),
  ((SELECT id
    FROM scientific_adviser
    WHERE lastname = 'Гущин'), 1),
  ((SELECT id
    FROM scientific_adviser
    WHERE lastname = 'Волков'), 3),
  ((SELECT id
    FROM scientific_adviser
    WHERE lastname = 'Умнов'), 2),
  ((SELECT id
    FROM scientific_adviser
    WHERE lastname = 'Еськин'), 2);

