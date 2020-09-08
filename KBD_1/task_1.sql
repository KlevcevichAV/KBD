drop table teacher;
drop table subject;
drop table student_group;
drop table teacher_student_group;

create table teacher
(
    PersonalNumber VARCHAR(4) PRIMARY KEY,
    LastName       VARCHAR(20),
    Position       VARCHAR(20),
    Chair          VARCHAR(20),
    Specialty      VARCHAR(50),
    HomePhone      int
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

create table subject
(
    SubjectCodeNumber VARCHAR(3) PRIMARY KEY,
    NameSubject       VARCHAR(20),
    NumberOfHours     INT,
    Specialty         VARCHAR(50),
    Semester          INT
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

create table student_group
(
    StudentGroupCodeNumber VARCHAR(3) PRIMARY KEY,
    StudentGroupName       VARCHAR(20),
    CountOfMembers         INT,
    Specialty              VARCHAR(50),
    LeaderLastName         text
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

create table teacher_student_group
(
    StudentGroupCodeNumber VARCHAR(3),
    SubjectCodeNumber      VARCHAR(3),
    PersonalNumber         VARCHAR(4),
    AudienceNumber         int,
    primary key (StudentGroupCodeNumber, SubjectCodeNumber, PersonalNumber)
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

insert into teacher (PersonalNumber, LastName, Position, Chair, Specialty, HomePhone)
values ('221Л', 'Фролов', 'Доцент', 'ЭВМ', 'АСОИ, ЭВМ', 487),
       ('222Л', 'Костин', 'Доцент', 'ЭВМ', 'ЭВМ', 543),
       ('225Л', 'Бойко', 'Профессор', 'АСУ', 'АСОИ, ЭВМ', 112),
       ('430Л', 'Глазов', 'Ассистент', 'ТФ', 'СД', 421),
       ('110Л', 'Петров', 'Ассистент', 'Экономики', 'Международная экономика', 324);

insert into subject (SubjectCodeNumber, NameSubject, NumberOfHours, Specialty, Semester)
values ('12П', 'Мини ЭВМ', 36, 'ЭВМ', 1),
       ('14П', 'ПЭВМ', 72, 'ЭВМ', 2),
       ('17П', 'СУБД ПК', 48, 'АСОИ', 4),
       ('18П', 'ВКСС', 52, 'АСОИ', 6),
       ('34П', 'Физика', 30, 'СД', 6),
       ('22П', 'Аудит', 24, 'Бухучёта', 3);

insert into student_group (StudentGroupCodeNumber, StudentGroupName, CountOfMembers, Specialty, LeaderLastName)
values ('8Г', 'Э-12', 18, 'ЭВМ', 'Иванова'),
       ('7Г', 'Э-15', 22, 'ЭВМ', 'Сеткин'),
       ('4Г', 'АС-9', 24, 'АСОИ', 'Балабанов'),
       ('3Г', 'АС-8', 20, 'АСОИ', 'Чижов'),
       ('17Г', 'С-14', 29, 'СД', 'Амросов'),
       ('12Г', 'М-6', 16, 'Международная экономика', 'Трубин'),
       ('10Г', 'Б-4', 21, 'Бухучёт', 'Зязюткин');

insert into teacher_student_group (StudentGroupCodeNumber, SubjectCodeNumber, PersonalNumber, AudienceNumber)
values ('8Г', '12П', '222Л', 112),
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
WHERE Specialty = 'ЭВМ';

#3. Получить личный номер преподавателя и номера аудиторий, в которых они преподают предмет с кодовым номером 18П.

SELECT distinct PersonalNumber, AudienceNumber
FROM teacher_student_group
WHERE SubjectCodeNumber = '18П';

#4. Получить номера предметов и названия предметов, которые ведет преподаватель Костин.

SELECT distinct s.КодовыйНомерПредмета, s.НазваниеПредмета
FROM subject s
         join teacher_student_group tsg on tsg.SubjectCodeNumber = s.SubjectCodeNumber
         join teacher t on t.PersonalNumber = tsg.PersonalNumber
WHERE t.LastName = 'Костин';

#5. Получить номер группы, в которой ведутся предметы преподавателем Фроловым.

SELECT distinct tsg.StudentGroupCodeNumber
FROM teacher_student_group tsg
         join teacher t on t.PersonalNumber = tsg.PersonalNumber
WHERE t.LastName = 'Фролов';

#6. Получить информацию о предметах, которые ведутся на специальности АСОИ.

select *
from subject
where Specialty = 'АСОИ';

#7. Получить информацию о преподавателях, которые ведут предметы на специальности АСОИ.

select *
from teacher
where Specialty like '%АСОИ%';

#8. Получить фамилии преподавателей, которые ведут предметы в 210 аудитории.

select LastName
from teacher t
         join teacher_student_group tsg on tsg.PersonalNumber = t.PersonalNumber
where tsg.AudienceNumber = 210
group by LastName;

#9. Получить названия предметов и названия групп, которые ведут занятия в аудиториях с 100 по 200.

select NameSubject, StudentGroupName
from subject s,
     student_group sg
         join teacher_student_group tsg
              on tsg.SubjectCodeNumber = s.SubjectCodeNumber and tsg.КодовыйНомерГруппы = sg.КодовыйНомерГруппы
where tsg.AudienceNumber > 99
  and tsg.AudienceNumber < 201;

#10. Получить номеров таких групп на специальностях которых две и более группы.

select sg1.КодовыйНомерГруппы
from student_group sg1
         join student_group sg2
              on sg1.Specialty = sg2.Specialty and sg1.КодовыйНомерГруппы != sg2.КодовыйНомерГруппы;

#11. Получить общее количество студентов, обучающихся на специальности ЭВМ.

select sum(CountOfMembers)
from student_group
where Specialty = 'ЭВМ';

#12. Получить номера преподавателей, обучающих студентов по специальности ЭВМ.

select PersonalNumber
from teacher
where Specialty like '%ЭВМ%';

#13. Получить номера предметов, изучаемых всеми студенческими группами.
select distinct SubjectCodeNumber
from teacher_student_group
group by SubjectCodeNumber
having count(1) = (select count(1) from student_group);

#14. Получить фамилии преподавателей, преподающих те же предметы, что и преподаватель преподающий предмет с номером 14П.

select distinct LastName
from teacher t
         join teacher_student_group tsg on t.ЛичныйНомер = tsg.ЛичныйНомер
where tsg.ЛичныйНомер in (
    select distinct PersonalNumber
    from teacher_student_group
    where SubjectCodeNumber in (
        select distinct SubjectCodeNumber
        from teacher_student_group
        where PersonalNumber in (
            select distinct PersonalNumber
            from teacher_student_group
            where SubjectCodeNumber = '14П')
    )
);

#15. Получить информацию о предметах, которые не ведет преподаватель с личным номером 221Л.


select *
from subject
where SubjectCodeNumber not in (
    select SubjectCodeNumber
    from teacher_student_group
    where PersonalNumber = '221Л'
    group by SubjectCodeNumber
);


#16. Получить информацию о предметах, которые не изучаются в группе М-6.

select *
from subject
where SubjectCodeNumber not in (
    select SubjectCodeNumber
    from teacher_student_group tsg
             join student_group sg on sg.StudentGroupCodeNumber = tsg.StudentGroupCodeNumber
    where sg.StudentGroupName = 'М-6'
);

#17. Получить информацию о доцентах, преподающих в группах 3Г и 8Г.
select *
from teacher
where Position = 'Доцент'
  and PersonalNumber in (
    select PersonalNumber
    from teacher_student_group
    where StudentGroupCodeNumber = '3Г'
)
  and PersonalNumber in (
    select PersonalNumber
    from teacher_student_group
    where StudentGroupCodeNumber = '8Г'
);

#18. Получить номера предметов, номера преподавателей, номера групп, в которых ведут занятия преподаватели с кафедры ЭВМ, имеющих специальность АСОИ.
select SubjectCodeNumber, StudentGroupCodeNumber, PersonalNumber
from teacher_student_group
where PersonalNumber in (
    select PersonalNumber
    from teacher
    where Chair = 'ЭВМ'
      and Specialty like '%АСОИ%'
);

#19. Получить номера групп с такой же специальностью, что и специальность преподавателей.

#20. Получить номера преподавателей с кафедры ЭВМ, преподающих предметы по специальности, совпадающей со специальностью студенческой группы.

#21. Получить специальности студенческой группы, на которых работают преподаватели кафедры АСУ.
select distinct sg.Специальность
from student_group sg
         join teacher_student_group tsg on tsg.КодовыйНомерГруппы = sg.КодовыйНомерГруппы
         join teacher t on t.ЛичныйНомер = tsg.ЛичныйНомер
where t.Кафедра = 'АСУ';

#22. Получить номера предметов, изучаемых группой АС-8.
select SubjectCodeNumber
from teacher_student_group tsg
         join student_group sg on sg.StudentGroupCodeNumber = tsg.StudentGroupCodeNumber
where sg.StudentGroupName = 'АС-8';

#23. Получить номера студенческих групп, которые изучают те же предметы, что и студенческая группа АС-8.
select distinct SubjectCodeNumber
from teacher_student_group tsg
         join student_group sg on sg.КодовыйНомерГруппы = tsg.КодовыйНомерГруппы
where sg.НазваниеГруппы = 'АС-8';

#24. Получить номера студенческих групп, которые не изучают предметы, преподаваемых в студенческой группе АС-8.
select StudentGroupCodeNumber
from teacher_student_group
where StudentGroupCodeNumber not in (
    select distinct StudentGroupCodeNumber
    from teacher_student_group
    where SubjectCodeNumber in (
        select distinct SubjectCodeNumber
        from teacher_student_group tsg
                 left join student_group sg on tsg.КодовыйНомерГруппы = sg.КодовыйНомерГруппы
        where StudentGroupName = 'АС-8'
    )
);
#25. Получить номера студенческих групп, которые не изучают предметы, преподаваемых преподавателем 430Л.
select StudentGroupCodeNumber
from teacher_student_group
group by StudentGroupCodeNumber
having StudentGroupCodeNumber not in (
    select distinct StudentGroupCodeNumber
    from teacher_student_group
    where SubjectCodeNumber in (
        select distinct SubjectCodeNumber
        from teacher_student_group
        where PersonalNumber = '430Л'
    )
);
#26. Получить номера преподавателей, работающих с группой Э-15, но не преподающих предмет 12П.
select PersonalNumber
from teacher_student_group tsg
         left join student_group sg on tsg.StudentGroupCodeNumber = sg.StudentGroupCodeNumber
where sg.StudentGroupName = 'Э-15'
group by PersonalNumber
having PersonalNumber not in (
    select distinct PersonalNumber from teacher_student_group where SubjectCodeNumber = '12П'
);
