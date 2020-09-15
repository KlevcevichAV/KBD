#19. Получить номера групп с такой же специальностью, что и специальность преподавателей.
SELECT DISTINCT sg.student_group_code
FROM teacher_student_group AS tsg
         LEFT JOIN student_group AS sg on tsg.student_group_code = sg.student_group_code
         LEFT JOIN teacher AS t on tsg.personal_number = t.personal_number
WHERE sg.specialty = t.specialty;