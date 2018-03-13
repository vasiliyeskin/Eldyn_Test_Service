DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS UserAnswers;
DROP TABLE IF EXISTS UserQuestions;
DROP TABLE IF EXISTS UserTests;
DROP TABLE IF EXISTS answerAndQuestions;
DROP TABLE IF EXISTS testAndQuestions;
DROP TABLE IF EXISTS test;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;
DROP SEQUENCE IF EXISTS global_seqTest;
DROP SEQUENCE IF EXISTS global_seqQuestion;
DROP SEQUENCE IF EXISTS global_seqAnswer;
DROP SEQUENCE IF EXISTS global_seqTQ;
DROP SEQUENCE IF EXISTS global_seqATQ;
DROP SEQUENCE IF EXISTS global_seqUserAnswer;
DROP SEQUENCE IF EXISTS global_seqUserQuestion;
DROP SEQUENCE IF EXISTS global_seqUserTest;

CREATE SEQUENCE global_seq              START 100000;
CREATE SEQUENCE global_seqTest          START 1;
CREATE SEQUENCE global_seqQuestion      START 1;
CREATE SEQUENCE global_seqAnswer        START 1;
CREATE SEQUENCE global_seqTQ            START 1;
CREATE SEQUENCE global_seqATQ           START 1;
CREATE SEQUENCE global_seqUserAnswer    START 1;
CREATE SEQUENCE global_seqUserQuestion  START 1;
CREATE SEQUENCE global_seqUserTest      START 1;



CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  firstname        VARCHAR(255)            NOT NULL,
  lastname         VARCHAR(255)            NOT NULL,
  email            VARCHAR(255)            NOT NULL,
  password         VARCHAR(255)            NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  active           BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE answer
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seqAnswer'),
  text             VARCHAR,
  image            OID,
  creationdatetime TIMESTAMP DEFAULT now() not NULL,
  creatorId        INTEGER,
  FOREIGN KEY (creatorId) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE question
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seqQuestion'),
  text             VARCHAR,
  image            OID,
  creationdatetime TIMESTAMP DEFAULT now() not NULL,
  creatorId        INTEGER,
  FOREIGN KEY (creatorId) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE test
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seqTest'),
  text             VARCHAR,
  image            OID,
  creationdatetime TIMESTAMP DEFAULT now() not NULL,
  creatorId        INTEGER,
  FOREIGN KEY (creatorId) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE testAndQuestions
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seqTQ'),
--   id               SERIAL PRIMARY KEY,
  testId           INTEGER NOT NULL,
  questionID       INTEGER NOT NULL,
  creationdatetime TIMESTAMP DEFAULT now() not NULL,
  creatorId        INTEGER,
  FOREIGN KEY (creatorId) REFERENCES USERS (id) ON DELETE CASCADE,
  FOREIGN KEY (testId) REFERENCES TEST (id) ON DELETE CASCADE,
  FOREIGN KEY (questionID) REFERENCES question (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX testAndQuestions_unique_idx ON testAndQuestions (testId, questionID);

CREATE TABLE answerAndQuestions
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seqATQ'),
  questionID         INTEGER NOT NULL,
  answerID           INTEGER NOT NULL,
  isRight            BOOLEAN,
  testAnswer         VARCHAR,
  creationdatetime   TIMESTAMP DEFAULT now() not NULL,
  creatorId          INTEGER,
  FOREIGN KEY (creatorId) REFERENCES USERS (id) ON DELETE CASCADE,
  FOREIGN KEY (questionID) REFERENCES question (id) ON DELETE CASCADE,
  FOREIGN KEY (answerID) REFERENCES answer (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX answerAndQuestions_unique_idx ON answerAndQuestions (questionID, answerID);


CREATE TABLE UserTests
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seqUserTest'),
  testID             INTEGER NOT NULL,
  creationdatetime   TIMESTAMP DEFAULT now() not NULL,
  userID             INTEGER NOT NULL,
  FOREIGN KEY (userID)         REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (testID)         REFERENCES test (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX UserTests_unique_idx ON UserTests (testID, userID, creationdatetime);


CREATE TABLE UserQuestions
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seqUserQuestion'),
  usertestID         INTEGER NOT NULL,
  questionTestID     INTEGER NOT NULL,
  creationdatetime   TIMESTAMP DEFAULT now() not NULL,
  userID             INTEGER NOT NULL,
  FOREIGN KEY (userID)         REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (questionTestID) REFERENCES testAndQuestions (id) ON DELETE CASCADE,
  FOREIGN KEY (usertestID)     REFERENCES UserTests (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX UserQuestion_unique_idx ON UserQuestions (usertestID, questionTestID, userID, creationdatetime);


CREATE TABLE UserAnswers
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seqUserAnswer'),
  userquestionID     INTEGER NOT NULL,
  answerID           INTEGER NOT NULL,
  isRight            BOOLEAN NOT NULL,
  testAnswer         VARCHAR,
  creationdatetime   TIMESTAMP DEFAULT now() not NULL,
  userID             INTEGER NOT NULL,
  FOREIGN KEY (userID) REFERENCES USERS (id) ON DELETE CASCADE,
  FOREIGN KEY (userquestionID) REFERENCES UserQuestions (id) ON DELETE CASCADE,
  FOREIGN KEY (answerID) REFERENCES answer (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX UserAnswer_unique_idx ON UserAnswers (userquestionID, answerID, creationdatetime);






-- Tables for Docs
DROP TABLE IF EXISTS students_practice;
DROP TABLE IF EXISTS students_scientificadviser;
DROP TABLE IF EXISTS adviser_position;
DROP TABLE IF EXISTS adviser_organization;

DROP TABLE IF EXISTS students;
DROP SEQUENCE IF EXISTS global_seq_student;
CREATE SEQUENCE global_seq_student          START 1;
CREATE TABLE students
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq_student'),
  firstname        VARCHAR(255)            NOT NULL,
  midlename        VARCHAR(255)                NULL,
  lastname         VARCHAR(255)            NOT NULL,
  course           INTEGER                 NOT NULL,
  email            VARCHAR(255)                NULL,
  phone            VARCHAR(255)                NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  active           BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE UNIQUE INDEX studnts_unique_firstname_lastname_idx ON students (firstname,lastname);


DROP TABLE IF EXISTS scientific_adviser;
DROP SEQUENCE IF EXISTS global_seq_sa;
CREATE SEQUENCE global_seq_sa          START 1;
CREATE TABLE scientific_adviser
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq_sa'),
  firstname        VARCHAR(255)            NOT NULL,
  middlename       VARCHAR(255)                NULL,
  lastname         VARCHAR(255)            NOT NULL,
  email            VARCHAR(255)                NULL,
  phone            VARCHAR(255)                NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL
);
CREATE UNIQUE INDEX sa_unique_firstname_lastname_idx ON scientific_adviser (firstname,lastname,middlename);

DROP TABLE IF EXISTS position_in_the_organization;
DROP SEQUENCE IF EXISTS global_seq_pio;
CREATE SEQUENCE global_seq_pio          START 1;
CREATE TABLE position_in_the_organization
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq_pio'),
  positionIO       VARCHAR(255)            NOT NULL
);
CREATE UNIQUE INDEX pio_unique_idx ON position_in_the_organization (positionIO);


DROP TABLE IF EXISTS organization;
DROP SEQUENCE IF EXISTS global_seq_o;
CREATE SEQUENCE global_seq_o          START 1;
CREATE TABLE organization
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq_o'),
  name             text                    NOT NULL,
  nameGenitive     text                    NOT NULL,
  shortname        VARCHAR(255)            NOT NULL
);
CREATE UNIQUE INDEX o_unique_idx ON organization (name);


DROP TABLE IF EXISTS practice;
DROP SEQUENCE IF EXISTS global_seq_practice;
CREATE SEQUENCE global_seq_practice          START 1;
CREATE TABLE practice
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq_practice'),
  name             text                    NOT NULL,
  nameDirection    text                    NOT NULL,
  startDate        TIMESTAMP               NOT NULL,
  endDate          TIMESTAMP               NOT NULL
);
CREATE UNIQUE INDEX practice_unique_idx ON practice (name);


CREATE TABLE students_practice
(
  student_id   INTEGER NOT NULL,
  practice_id  INTEGER NOT NULL,
  CONSTRAINT student_practice_idx UNIQUE (student_id, practice_id),
  FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE,
  FOREIGN KEY (practice_id) REFERENCES practice (id) ON DELETE CASCADE
);


CREATE TABLE students_scientificadviser
(
  student_id   INTEGER NOT NULL,
  adiver_id  INTEGER NOT NULL,
  CONSTRAINT student_advise_idx UNIQUE (student_id, adiver_id),
  FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE,
  FOREIGN KEY (adiver_id) REFERENCES scientific_adviser (id) ON DELETE CASCADE
);


CREATE TABLE adviser_position
(
  adiver_id  INTEGER NOT NULL,
  position_id   INTEGER NOT NULL,
  CONSTRAINT advise_position_idx UNIQUE (adiver_id, position_id),
  FOREIGN KEY (adiver_id) REFERENCES scientific_adviser (id) ON DELETE CASCADE,
    FOREIGN KEY (position_id) REFERENCES position_in_the_organization (id) ON DELETE CASCADE
);


CREATE TABLE adviser_organization
(
  adiver_id         INTEGER NOT NULL,
  organization_id   INTEGER NOT NULL,
  CONSTRAINT adviser_organization_idx UNIQUE (adiver_id, organization_id),
  FOREIGN KEY (adiver_id) REFERENCES scientific_adviser (id) ON DELETE CASCADE,
  FOREIGN KEY (organization_id) REFERENCES organization (id) ON DELETE CASCADE
);
