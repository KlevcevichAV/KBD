# 18 Получить номера деталей, поставляемых для некоторого проекта со средним количеством больше 320.
SELECT part_id
FROM provider_part_project
GROUP BY part_id
HAVING avg(sum) > 320;
