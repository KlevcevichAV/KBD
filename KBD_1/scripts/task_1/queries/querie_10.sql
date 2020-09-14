#10. Получить номеров таких групп на специальностях которых две и более группы.
SELECT sg1.student_group_code
FROM student_group AS sg1
   , student_group AS sg2
where sg1.specialty = sg2.specialty
  AND sg1.student_group_code != sg2.student_group_code;
