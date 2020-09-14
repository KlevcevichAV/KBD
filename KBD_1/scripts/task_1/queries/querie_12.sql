#12. Получить номера преподавателей, обучающих студентов по специальности ЭВМ.
SELECT personal_number
FROM teacher
WHERE specialty LIKE '%ЭВМ%';
