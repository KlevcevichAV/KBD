DROP TABLE teacher;
DROP TABLE subject;
DROP TABLE student_group;
DROP TABLE teacher_student_group;

CREATE TABLE teacher
(
    personal_number VARCHAR(4) PRIMARY KEY,
    last_name       VARCHAR(20),
    position        VARCHAR(20),
    chair           VARCHAR(20),
    specialty       VARCHAR(50),
    home_phone      INT
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

CREATE TABLE subject
(
    subject_code_number VARCHAR(3) PRIMARY KEY,
    name_subject        VARCHAR(20),
    number_of_hours     INT,
    specialty           VARCHAR(50),
    semester            INT
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

CREATE TABLE student_group
(
    student_group_code VARCHAR(3) PRIMARY KEY,
    student_group_name VARCHAR(20),
    count_of_members   INT,
    specialty          VARCHAR(50),
    leader_last_name   VARCHAR(10)
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

CREATE TABLE teacher_student_group
(
    student_group_code  VARCHAR(3),
    subject_code_number VARCHAR(3),
    personal_number     VARCHAR(4),
    audience_number     INT,
    PRIMARY KEY (student_group_code, subject_code_number, personal_number)
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

INSERT INTO teacher (personal_number, last_name, position, chair, specialty, home_phone)
VALUES ('221Л', 'Фролов', 'Доцент', 'ЭВМ', 'АСОИ, ЭВМ', 487),
       ('222Л', 'Костин', 'Доцент', 'ЭВМ', 'ЭВМ', 543),
       ('225Л', 'Бойко', 'Профессор', 'АСУ', 'АСОИ, ЭВМ', 112),
       ('430Л', 'Глазов', 'Ассистент', 'ТФ', 'СД', 421),
       ('110Л', 'Петров', 'Ассистент', 'Экономики', 'Международная экономика', 324);

INSERT INTO subject (subject_code_number, name_subject, number_of_hours, specialty, semester)
VALUES ('12П', 'Мини ЭВМ', 36, 'ЭВМ', 1),
       ('14П', 'ПЭВМ', 72, 'ЭВМ', 2),
       ('17П', 'СУБД ПК', 48, 'АСОИ', 4),
       ('18П', 'ВКСС', 52, 'АСОИ', 6),
       ('34П', 'Физика', 30, 'СД', 6),
       ('22П', 'Аудит', 24, 'Бухучёта', 3);

INSERT INTO student_group (student_group_code, student_group_name, count_of_members, specialty, leader_last_name)
VALUES ('8Г', 'Э-12', 18, 'ЭВМ', 'Иванова'),
       ('7Г', 'Э-15', 22, 'ЭВМ', 'Сеткин'),
       ('4Г', 'АС-9', 24, 'АСОИ', 'Балабанов'),
       ('3Г', 'АС-8', 20, 'АСОИ', 'Чижов'),
       ('17Г', 'С-14', 29, 'СД', 'Амросов'),
       ('12Г', 'М-6', 16, 'Международная экономика', 'Трубин'),
       ('10Г', 'Б-4', 21, 'Бухучёт', 'Зязюткин');

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


# 1. Получить полную информацию обо всех преподавателях.

SELECT *
FROM teacher;


#2. Получить полную информацию обо всех студенческих группах на специальности ЭВМ.

SELECT *
FROM subject
WHERE specialty = 'ЭВМ';

#3. Получить личный номер преподавателя и номера аудиторий, в которых они преподают предмет с кодовым номером 18П.

SELECT DISTINCT personal_number, audience_number
FROM teacher_student_group
WHERE subject_code_number = '18П';

#4. Получить номера предметов и названия предметов, которые ведет преподаватель Костин.

SELECT DISTINCT s.subject_code_number, s.name_subject
FROM subject AS s
         LEFT JOIN teacher_student_group AS tsg ON tsg.subject_code_number = s.subject_code_number
         left join teacher AS t on tsg.personal_number = t.personal_number
WHERE t.last_name = 'Костин';

#5. Получить номер группы, в которой ведутся предметы преподавателем Фроловым.

SELECT DISTINCT tsg.student_group_code
FROM teacher_student_group AS tsg
         LEFT JOIN teacher AS t ON t.personal_number = tsg.personal_number
WHERE t.last_name = 'Фролов';

#6. Получить информацию о предметах, которые ведутся на специальности АСОИ.

SELECT *
FROM subject
WHERE Specialty = 'АСОИ';

#7. Получить информацию о преподавателях, которые ведут предметы на специальности АСОИ.

SELECT *
FROM teacher
WHERE Specialty LIKE '%АСОИ%';

#8. Получить фамилии преподавателей, которые ведут предметы в 210 аудитории.

SELECT last_name
FROM teacher AS t
         LEFT JOIN teacher_student_group AS tsg ON tsg.personal_number = t.personal_number
WHERE tsg.audience_number = 210
GROUP BY last_name;

#9. Получить названия предметов и названия групп, которые ведут занятия в аудиториях с 100 по 200.

SELECT name_subject, student_group_name
FROM subject AS s
         LEFT JOIN teacher_student_group AS tsg
                   ON tsg.subject_code_number = s.subject_code_number
         LEFT JOIN student_group sg on tsg.student_group_code = sg.student_group_code
WHERE tsg.audience_number > 99
  AND tsg.audience_number < 201;

#10. Получить номеров таких групп на специальностях которых две и более группы.

SELECT sg1.student_group_code
FROM student_group AS sg1
   , student_group AS sg2
where sg1.specialty = sg2.specialty
  AND sg1.student_group_code != sg2.student_group_code;

#11. Получить общее количество студентов, обучающихся на специальности ЭВМ.

SELECT sum(count_of_members) AS count
FROM student_group
WHERE Specialty = 'ЭВМ';

#12. Получить номера преподавателей, обучающих студентов по специальности ЭВМ.

SELECT personal_number
FROM teacher
WHERE specialty LIKE '%ЭВМ%';

#13. Получить номера предметов, изучаемых всеми студенческими группами.
SELECT DISTINCT subject_code_number
FROM teacher_student_group
GROUP BY subject_code_number
HAVING count(1) = (SELECT count(1) FROM student_group);

#14. Получить фамилии преподавателей, преподающих те же предметы, что и преподаватель преподающий предмет с номером 14П.

SELECT DISTINCT last_name
FROM teacher AS t
         LEFT JOIN teacher_student_group AS tsg ON t.personal_number = tsg.personal_number
WHERE tsg.personal_number IN (
    SELECT DISTINCT tsg.personal_number
    FROM teacher_student_group
    WHERE subject_code_number IN (
        SELECT DISTINCT subject_code_number
        FROM teacher_student_group
        WHERE personal_number IN (
            SELECT DISTINCT personal_number
            FROM teacher_student_group
            WHERE subject_code_number = '14П')
    )
);

#15. Получить информацию о предметах, которые не ведет преподаватель с личным номером 221Л.
SELECT *
FROM subject
WHERE subject_code_number NOT IN (
    SELECT subject_code_number
    FROM teacher_student_group
    WHERE personal_number = '221Л'
    GROUP BY subject_code_number
);


#16. Получить информацию о предметах, которые не изучаются в группе М-6.
SELECT *
FROM subject
WHERE subject_code_number NOT IN (
    SELECT tsg.subject_code_number
    FROM teacher_student_group AS tsg
             LEFT JOIN student_group AS sg ON sg.student_group_code = tsg.student_group_code
    WHERE sg.student_group_name = 'М-6'
);

#17. Получить информацию о доцентах, преподающих в группах 3Г и 8Г.
SELECT *
FROM teacher
WHERE Position = 'Доцент'
  AND personal_number IN (
    SELECT personal_number
    FROM teacher_student_group
    WHERE student_group_code = '3Г'
)
  AND personal_number IN (
    SELECT personal_number
    FROM teacher_student_group
    WHERE student_group_code = '8Г'
);

#18. Получить номера предметов, номера преподавателей, номера групп, в которых ведут занятия преподаватели с кафедры ЭВМ, имеющих специальность АСОИ.
SELECT subject_code_number, personal_number, student_group_code
FROM teacher_student_group
WHERE personal_number IN (
    SELECT personal_number
    FROM teacher
    WHERE Chair = 'ЭВМ'
      AND Specialty LIKE '%АСОИ%'
);

#19. Получить номера групп с такой же специальностью, что и специальность преподавателей.

#20. Получить номера преподавателей с кафедры ЭВМ, преподающих предметы по специальности, совпадающей со специальностью студенческой группы.

#21. Получить специальности студенческой группы, на которых работают преподаватели кафедры АСУ.
SELECT DISTINCT sg.specialty
FROM student_group AS sg
         left join teacher_student_group AS tsg on sg.student_group_code = tsg.student_group_code
         left join teacher AS t on tsg.personal_number = t.personal_number
WHERE t.chair = 'АСУ';

#22. Получить номера предметов, изучаемых группой АС-8.
SELECT subject_code_number
FROM teacher_student_group AS tsg
         LEFT JOIN student_group AS sg ON sg.student_group_code = tsg.student_group_code
WHERE sg.student_group_name = 'АС-8';

#23. Получить номера студенческих групп, которые изучают те же предметы, что и студенческая группа АС-8.
SELECT DISTINCT student_group_code
FROM teacher_student_group
WHERE subject_code_number IN (
    SELECT DISTINCT tsg.subject_code_number
    FROM teacher_student_group AS tsg
             left join student_group AS sg on tsg.student_group_code = sg.student_group_code
    WHERE sg.student_group_name = 'АС-8'
);
#24. Получить номера студенческих групп, которые не изучают предметы, преподаваемых в студенческой группе АС-8.
SELECT distinct student_group_code
FROM teacher_student_group
WHERE student_group_code NOT IN (
    SELECT DISTINCT student_group_code
    FROM teacher_student_group
    WHERE subject_code_number IN (
        SELECT DISTINCT tsg.subject_code_number
        FROM teacher_student_group AS tsg
                 left join student_group AS sg on tsg.student_group_code = sg.student_group_code
        WHERE sg.student_group_name = 'АС-8'
    )
);

#25. Получить номера студенческих групп, которые не изучают предметы, преподаваемых преподавателем 430Л.
SELECT student_group_code
FROM teacher_student_group
GROUP BY student_group_code
HAVING student_group_code NOT IN (
    SELECT DISTINCT student_group_code
    FROM teacher_student_group
    WHERE personal_number = '430Л'
);


#26. Получить номера преподавателей, работающих с группой Э-15, но не преподающих предмет 12П.
SELECT personal_number
FROM teacher_student_group AS tsg
         LEFT JOIN student_group AS sg ON tsg.student_group_code = sg.student_group_code
WHERE sg.student_group_name = 'Э-15'
GROUP BY personal_number
HAVING personal_number NOT IN (
    SELECT DISTINCT tsg.personal_number FROM teacher_student_group WHERE subject_code_number = '12П'
);


