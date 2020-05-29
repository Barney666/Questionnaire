DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER`
(
  `id`       int         NOT NULL AUTO_INCREMENT,
  `name`     varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `PAPER`;
CREATE TABLE `PAPER`
(
  `id`          int         NOT NULL AUTO_INCREMENT,
  `user_id`     int         NOT NULL,
  `title`       varchar(64) NOT NULL,
  `description` varchar(64) NOT NULL,
  `start_time`  datetime,
  `end_time`    datetime,
  `status`      varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `QUESTION`;
CREATE TABLE `QUESTION`
(
  `id`       int         NOT NULL AUTO_INCREMENT,
  `paper_id` int         NOT NULL,
  `type`     int,        -- 因为一开始添加问卷是只给paperId 剩下是空的 所以只能可以为null了
  `title`    varchar(64),
  PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `OPTIONS`;
CREATE TABLE `OPTIONS`             -- OPTION为保留关键字，所以只能用复数形式了
(
  `question_id` int         NOT NULL,
  `sequence`    int         NOT NULL,
  `content`     varchar(64) NOT NULL,
  PRIMARY KEY (`question_id`,`sequence`)
);
DROP TABLE IF EXISTS `ANSWER`;
CREATE TABLE `ANSWER`
(
  `id`            int          NOT NULL AUTO_INCREMENT,
  `question_id`   int          NOT NULL,
  `paper_id`      int          NOT NULL,
  `question_type` int          NOT NULL,
  `create_time`   datetime     NOT NULL,
  `answer_content` varchar(512) NOT NULL ,
  PRIMARY KEY (`id`)
);

INSERT INTO USER(PASSWORD, NAME) VALUES ('admin','admin');

INSERT INTO PAPER(USER_ID,TITLE,DESCRIPTION, START_TIME, END_TIME, STATUS) VALUES (1,'测试问卷1','这是一个问卷','2020-04-22 00:00:00','2020-06-15 00:00:00','INIT');

insert into QUESTION(paper_id, type, title) values (1,1,'第一个问题 单选题');
insert into QUESTION(paper_id, type, title) values (1,2,'第二个问题 多选题');
insert into QUESTION(paper_id, type, title) values (1,3,'第三个问题 简答题');

insert into OPTIONS(question_id, sequence, content) values (1,1,'单选A');
insert into OPTIONS(question_id, sequence, content) values (1,2,'单选B');
insert into OPTIONS(question_id, sequence, content) values (2,1,'多选A');
insert into OPTIONS(question_id, sequence, content) values (2,2,'多选B');
insert into OPTIONS(question_id, sequence, content) values (2,3,'多选C');

INSERT INTO ANSWER(QUESTION_ID, PAPER_ID, QUESTION_TYPE, CREATE_TIME, ANSWER_CONTENT) VALUES (1,1,1,'2020-04-22 19:00:00','1');
INSERT INTO ANSWER(QUESTION_ID, PAPER_ID, QUESTION_TYPE, CREATE_TIME, ANSWER_CONTENT) VALUES (2,1,2,'2020-04-22 19:00:00','3,4');
INSERT INTO ANSWER(QUESTION_ID, PAPER_ID, QUESTION_TYPE, CREATE_TIME, ANSWER_CONTENT) VALUES (3,1,3,'2020-04-22 19:00:00','我是简答题答案');