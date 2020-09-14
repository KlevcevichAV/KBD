# 31 Получить номера поставщиков, поставляющих одну и ту же деталь для всех проектов.
SELECT t2.producer
FROM (
         SELECT t.provider producer
         FROM (
                  SELECT project_id project, provider_id provider, part_id part
                  FROM provider_part_project ppp
                  GROUP BY project, provider, part
              ) AS t
         GROUP BY t.provider, t.part, t.part
     ) t2
GROUP BY t2.producer
HAVING COUNT(*) = (SELECT COUNT(*) FROM project);

