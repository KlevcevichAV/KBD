#20. Получить номера преподавателей с кафедры ЭВМ, преподающих предметы по специальности, совпадающей со специальностью студенческой группы.
SELECT DISTINCT personal_number
FROM teacher_student_group AS tsg
         LEFT JOIN student_group AS sg on tsg.student_group_code = sg.student_group_code
         left join subject AS s on tsg.subject_code_number = s.subject_code_number
WHERE sg.specialty = s.specialty;