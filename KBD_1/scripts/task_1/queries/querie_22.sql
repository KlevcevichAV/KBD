#22. Получить номера предметов, изучаемых группой АС-8.
SELECT subject_code_number
FROM teacher_student_group AS tsg
         LEFT JOIN student_group AS sg ON sg.student_group_code = tsg.student_group_code
WHERE sg.student_group_name = 'АС-8';
