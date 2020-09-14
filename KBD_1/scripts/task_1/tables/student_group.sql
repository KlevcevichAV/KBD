CREATE TABLE student_group
(
    student_group_code VARCHAR(3) PRIMARY KEY,
    student_group_name VARCHAR(20),
    count_of_members   INT,
    specialty          VARCHAR(50),
    leader_last_name   VARCHAR(10)
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

INSERT INTO student_group (student_group_code, student_group_name, count_of_members, specialty, leader_last_name)
VALUES ('8Г', 'Э-12', 18, 'ЭВМ', 'Иванова'),
       ('7Г', 'Э-15', 22, 'ЭВМ', 'Сеткин'),
       ('4Г', 'АС-9', 24, 'АСОИ', 'Балабанов'),
       ('3Г', 'АС-8', 20, 'АСОИ', 'Чижов'),
       ('17Г', 'С-14', 29, 'СД', 'Амросов'),
       ('12Г', 'М-6', 16, 'Международная экономика', 'Трубин'),
       ('10Г', 'Б-4', 21, 'Бухучёт', 'Зязюткин');
