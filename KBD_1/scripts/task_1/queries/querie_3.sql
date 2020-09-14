#3. Получить личный номер преподавателя и номера аудиторий, в которых они преподают предмет с кодовым номером 18П.
SELECT DISTINCT personal_number, audience_number
FROM teacher_student_group
WHERE subject_code_number = '18П';
