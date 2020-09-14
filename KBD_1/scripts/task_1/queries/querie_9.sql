#9. Получить названия предметов и названия групп, которые ведут занятия в аудиториях с 100 по 200.
SELECT name_subject, student_group_name
FROM subject AS s
         LEFT JOIN teacher_student_group AS tsg
                   ON tsg.subject_code_number = s.subject_code_number
         LEFT JOIN student_group sg on tsg.student_group_code = sg.student_group_code
WHERE tsg.audience_number > 99
  AND tsg.audience_number < 201;
