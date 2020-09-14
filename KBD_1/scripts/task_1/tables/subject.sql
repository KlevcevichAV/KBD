CREATE TABLE subject
(
    subject_code_number VARCHAR(3) PRIMARY KEY,
    name_subject        VARCHAR(20),
    number_of_hours     INT,
    specialty           VARCHAR(50),
    semester            INT
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

INSERT INTO subject (subject_code_number, name_subject, number_of_hours, specialty, semester)
VALUES ('12П', 'Мини ЭВМ', 36, 'ЭВМ', 1),
       ('14П', 'ПЭВМ', 72, 'ЭВМ', 2),
       ('17П', 'СУБД ПК', 48, 'АСОИ', 4),
       ('18П', 'ВКСС', 52, 'АСОИ', 6),
       ('34П', 'Физика', 30, 'СД', 6),
       ('22П', 'Аудит', 24, 'Бухучёта', 3);
