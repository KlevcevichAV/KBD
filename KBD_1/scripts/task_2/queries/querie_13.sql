# 13 Получить номера проектов, обеспечиваемых по крайней мере одним поставщиком не из того же города.
SELECT DISTINCT p2.project_id
FROM provider_part_project AS ppp
         LEFT JOIN provider AS p ON ppp.provider_id = p.provider_id
         LEFT JOIN project AS p2 ON ppp.project_id = p2.project_id
WHERE p.city != p2.city;

