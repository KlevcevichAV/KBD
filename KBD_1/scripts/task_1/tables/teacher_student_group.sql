CREATE TABLE teacher_student_group
(
    student_group_code  VARCHAR(3),
    subject_code_number VARCHAR(3),
    personal_number     VARCHAR(4),
    audience_number     INT,
    PRIMARY KEY (student_group_code, subject_code_number, personal_number)
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

INSERT INTO teacher_student_group (student_group_code, subject_code_number, personal_number, audience_number)
VALUES ('8Г', '12П', '222Л', 112),
       ('8Г', '14П', '221Л', 220),
       ('8Г', '17П', '222Л', 112),
       ('7Г', '14П', '221Л', 220),
       ('7Г', '17П', '222Л', 241),
       ('7Г', '18П', '225Л', 210),
       ('4Г', '12П', '222Л', 112),
       ('4Г', '18П', '225Л', 210),
       ('3Г', '12П', '222Л', 112),
       ('3Г', '17П', '221Л', 241),
       ('3Г', '18П', '225Л', 210),
       ('17Г', '12П', '222Л', 112),
       ('17Г', '22П', '110Л', 220),
       ('17Г', '34П', '430Л', 118),
       ('12Г', '12П', '222Л', 112),
       ('12Г', '22П', '110Л', 210),
       ('10Г', '12П', '222Л', 210),
       ('10Г', '22П', '110Л', 210);
