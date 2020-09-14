#26. Получить номера преподавателей, работающих с группой Э-15, но не преподающих предмет 12П.
SELECT personal_number
FROM teacher_student_group AS tsg
         LEFT JOIN student_group AS sg ON tsg.student_group_code = sg.student_group_code
WHERE sg.student_group_name = 'Э-15'
GROUP BY personal_number
HAVING personal_number NOT IN (
    SELECT DISTINCT tsg.personal_number FROM teacher_student_group WHERE subject_code_number = '12П'
);


