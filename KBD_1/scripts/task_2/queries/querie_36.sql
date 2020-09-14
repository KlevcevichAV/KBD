# 36 Получить все пары номеров поставщиков, скажем, Пx и Пy, такие, что оба эти поставщика поставляют в точности одно и то же множество деталей.
SELECT t.provider_id, t2.provider_id
FROM (
         SELECT provider_id, SUM(t.sum) AS S
         FROM provider_part_project t
         GROUP BY provider_id
     ) AS t,
     (
         SELECT provider_id, SUM(sum) AS S
         FROM provider_part_project
         GROUP BY provider_id
     ) AS t2
WHERE t2.S = t.S
  AND t.provider_id <> t2.provider_id;

