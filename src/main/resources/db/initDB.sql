DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS UserAnswer;
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

CREATE SEQUENCE global_seq           START 100000;
CREATE SEQUENCE global_seqTest       START 1;
CREATE SEQUENCE global_seqQuestion   START 1;
CREATE SEQUENCE global_seqAnswer     START 1;
CREATE SEQUENCE global_seqTQ         START 1;
CREATE SEQUENCE global_seqATQ        START 1;
CREATE SEQUENCE global_seqUserAnswer START 1;


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
  testId           INTEGER,
  questionID       INTEGER,
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
  questionID         INTEGER,
  answerID           INTEGER,
  isRight            BOOLEAN,
  testAnswer         VARCHAR,
  creationdatetime   TIMESTAMP DEFAULT now() not NULL,
  creatorId          INTEGER,
  FOREIGN KEY (creatorId) REFERENCES USERS (id) ON DELETE CASCADE,
  FOREIGN KEY (questionID) REFERENCES question (id) ON DELETE CASCADE,
  FOREIGN KEY (answerID) REFERENCES answer (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX answerAndQuestions_unique_idx ON answerAndQuestions (questionID, answerID);

CREATE TABLE UserAnswer
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seqUserAnswer'),
  questionID         INTEGER,
  answerID           INTEGER,
  isRight            BOOLEAN,
  testAnswer         VARCHAR,
  creationdatetime   TIMESTAMP DEFAULT now() not NULL,
  userID          INTEGER,
  FOREIGN KEY (userID) REFERENCES USERS (id) ON DELETE CASCADE,
  FOREIGN KEY (questionID) REFERENCES testAndQuestions (id) ON DELETE CASCADE,
  FOREIGN KEY (answerID) REFERENCES answer (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX UserAnswer_unique_idx ON UserAnswer (questionID, answerID, creationdatetime);