#21. Получить специальности студенческой группы, на которых работают преподаватели кафедры АСУ.
SELECT DISTINCT sg.specialty
FROM student_group AS sg
         left join teacher_student_group AS tsg on sg.student_group_code = tsg.student_group_code
         left join teacher AS t on tsg.personal_number = t.personal_number
WHERE t.chair = 'АСУ';
