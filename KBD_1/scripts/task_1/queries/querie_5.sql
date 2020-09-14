#5. Получить номер группы, в которой ведутся предметы преподавателем Фроловым.
SELECT DISTINCT tsg.student_group_code
FROM teacher_student_group AS tsg
         LEFT JOIN teacher AS t ON t.personal_number = tsg.personal_number
WHERE t.last_name = 'Фролов';
