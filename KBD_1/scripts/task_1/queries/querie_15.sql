#15. Получить информацию о предметах, которые не ведет преподаватель с личным номером 221Л.
SELECT *
FROM subject
WHERE subject_code_number NOT IN (
    SELECT subject_code_number
    FROM teacher_student_group
    WHERE personal_number = '221Л'
    GROUP BY subject_code_number
);
