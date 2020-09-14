#11. Получить общее количество студентов, обучающихся на специальности ЭВМ.
SELECT sum(count_of_members) AS count
FROM student_group
WHERE Specialty = 'ЭВМ';
