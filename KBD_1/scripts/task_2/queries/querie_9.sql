# 9 Получить номера деталей, поставляемых поставщиком в Лондоне.
SELECT DISTINCT part_id
FROM provider_part_project AS ppp
         LEFT JOIN project AS p ON ppp.project_id = p.project_id
WHERE p.city = 'Псков';

