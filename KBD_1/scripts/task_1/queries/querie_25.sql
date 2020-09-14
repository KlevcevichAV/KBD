#25. Получить номера студенческих групп, которые не изучают предметы, преподаваемых преподавателем 430Л.
SELECT student_group_code
FROM teacher_student_group
GROUP BY student_group_code
HAVING student_group_code NOT IN (
    SELECT DISTINCT student_group_code
    FROM teacher_student_group
    WHERE personal_number = '430Л'
);
