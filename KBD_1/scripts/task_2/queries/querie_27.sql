# 27 Получить номера поставщиков, поставляющих деталь Д1 для некоторого проекта в количестве, большем среднего количества деталей Д1 в поставках для этого проекта.
SELECT provider_id
FROM provider_part_project AS ppp,
     (SELECT project_id, avg(sum) AS mean
      FROM provider_part_project
      GROUP BY project_id, part_id
      having part_id = 'Д1') AS t
WHERE t.project_id = ppp.project_id
  AND sum > mean
  AND part_id = 'Д1';
