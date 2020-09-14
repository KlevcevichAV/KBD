#8. Получить фамилии преподавателей, которые ведут предметы в 210 аудитории.
SELECT last_name
FROM teacher AS t
         LEFT JOIN teacher_student_group AS tsg ON tsg.personal_number = t.personal_number
WHERE tsg.audience_number = 210
GROUP BY last_name;
