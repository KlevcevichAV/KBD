#17. Получить информацию о доцентах, преподающих в группах 3Г и 8Г.
SELECT *
FROM teacher
WHERE Position = 'Доцент'
  AND personal_number IN (
    SELECT personal_number
    FROM teacher_student_group
    WHERE student_group_code = '3Г'
)
  AND personal_number IN (
    SELECT personal_number
    FROM teacher_student_group
    WHERE student_group_code = '8Г'
);
