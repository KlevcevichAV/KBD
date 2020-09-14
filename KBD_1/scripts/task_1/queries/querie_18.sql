#18. Получить номера предметов, номера преподавателей, номера групп, в которых ведут занятия преподаватели с кафедры ЭВМ, имеющих специальность АСОИ.
SELECT subject_code_number, personal_number, student_group_code
FROM teacher_student_group
WHERE personal_number IN (
    SELECT personal_number
    FROM teacher
    WHERE Chair = 'ЭВМ'
      AND Specialty LIKE '%АСОИ%'
);
