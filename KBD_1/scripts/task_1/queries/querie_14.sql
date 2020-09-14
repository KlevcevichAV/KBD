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
