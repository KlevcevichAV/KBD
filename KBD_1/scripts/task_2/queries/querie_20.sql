# 20 Получить цвета деталей, поставляемых поставщиком П1.
SELECT DISTINCT color
FROM part AS p
         LEFT JOIN provider_part_project AS ppp ON p.part_id = ppp.part_id
WHERE ppp.provider_id = 'П1';
