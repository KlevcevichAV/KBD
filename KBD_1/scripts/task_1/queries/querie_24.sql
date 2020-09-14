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
