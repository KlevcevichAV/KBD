#4. Получить номера предметов и названия предметов, которые ведет преподаватель Костин.
SELECT DISTINCT s.subject_code_number, s.name_subject
FROM subject AS s
         LEFT JOIN teacher_student_group AS tsg ON tsg.subject_code_number = s.subject_code_number
         left join teacher AS t on tsg.personal_number = t.personal_number
WHERE t.last_name = 'Костин';
