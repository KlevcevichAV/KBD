#13. Получить номера предметов, изучаемых всеми студенческими группами.
SELECT DISTINCT subject_code_number
FROM teacher_student_group
GROUP BY subject_code_number
HAVING count(1) = (SELECT count(1) FROM student_group);
