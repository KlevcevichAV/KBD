DROP TABLE provider;
DROP TABLE part;
DROP TABLE project;
DROP TABLE provider_part_project;

CREATE TABLE provider
(
    provider_id   VARCHAR(2) PRIMARY KEY,
    provider_name VARCHAR(10),
    status        int,
    city          VARCHAR(10)
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;
CREATE TABLE part
(
    part_id   VARCHAR(2) PRIMARY KEY,
    part_name VARCHAR(10),
    color     VARCHAR(10),
    size      INT,
    city      VARCHAR(10)
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

CREATE TABLE project
(
    project_id   VARCHAR(3) PRIMARY KEY,
    project_name VARCHAR(4),
    city         VARCHAR(10)
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

CREATE TABLE provider_part_project
(
    provider_id VARCHAR(2),
    part_id     VARCHAR(2),
    project_id  VARCHAR(3),
    sum         INT,
    PRIMARY KEY (provider_id, part_id, project_id)
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

INSERT INTO provider (provider_id, provider_name, status, city)
VALUES ('П1', 'Петров', 20, 'Москва'),
       ('П2', 'Синицин', 10, 'Таллинн'),
       ('П3', 'Федоров', 30, 'Таллинн'),
       ('П4', 'Чаянов', 20, 'Минск'),
       ('П5', 'Крюков', 30, 'Киев');

INSERT INTO part (part_id, part_name, color, size, city)
VALUES ('Д1', 'Болт', 'Красный', 12, 'Москва'),
       ('Д2', 'Гайка', 'Зелёная', 17, 'Минск'),
       ('Д3', 'Диск', 'Черный', 17, 'Вильнюс'),
       ('Д4', 'Диск', 'Черный', 14, 'Москва'),
       ('Д5', 'Корпус', 'Красный', 12, 'Минск'),
       ('Д6', 'Крышки', 'Красный', 19, 'Москва');

INSERT INTO project (project_id, project_name, city)
VALUES ('ПР1', 'ИПР1', 'Минск'),
       ('ПР2', 'ИПР2', 'Таллинн'),
       ('ПР3', 'ИПР3', 'Псков'),
       ('ПР4', 'ИПР4', 'Псков'),
       ('ПР5', 'ИПР5', 'Москва'),
       ('ПР6', 'ИПР6', 'Саратов'),
       ('ПР7', 'ИПР7', 'Москва');
INSERT INTO provider_part_project (provider_id, part_id, project_id, sum)
VALUES ('П1', 'Д1', 'ПР1', 200),
       ('П1', 'Д1', 'ПР2', 700),
       ('П2', 'Д3', 'ПР1', 400),
       ('П2', 'Д2', 'ПР2', 200),
       ('П2', 'Д3', 'ПР3', 200),
       ('П2', 'Д3', 'ПР4', 500),
       ('П2', 'Д3', 'ПР5', 600),
       ('П2', 'Д3', 'ПР6', 400),
       ('П2', 'Д3', 'ПР7', 800),
       ('П2', 'Д5', 'ПР2', 100),
       ('П3', 'Д3', 'ПР1', 200),
       ('П3', 'Д4', 'ПР2', 500),
       ('П4', 'Д6', 'ПР3', 300),
       ('П4', 'Д6', 'ПР7', 300),
       ('П5', 'Д2', 'ПР2', 200),
       ('П5', 'Д2', 'ПР4', 100),
       ('П5', 'Д5', 'ПР5', 500),
       ('П5', 'Д5', 'ПР7', 100),
       ('П5', 'Д6', 'ПР2', 200),
       ('П5', 'Д1', 'ПР2', 100),
       ('П5', 'Д3', 'ПР4', 200),
       ('П5', 'Д4', 'ПР4', 800),
       ('П5', 'Д5', 'ПР4', 400),
       ('П5', 'Д6', 'ПР4', 500);

# 20 Получить цвета деталей, поставляемых поставщиком П1.
SELECT DISTINCT color
FROM part AS p
         LEFT JOIN provider_part_project AS ppp ON p.part_id = ppp.part_id
WHERE ppp.provider_id = 'П1';
# 23 Получить номера поставщиков, поставляющих по крайней мере одну деталь, поставляемую по крайней мере одним поставщиком, который поставляет по крайней мере одну красную деталь.
SELECT DISTINCT provider_id
FROM provider_part_project
WHERE part_id IN (
    SELECT ppp.part_id
    FROM provider_part_project AS ppp
    WHERE ppp.provider_id IN (
        SELECT DISTINCT provider_id
        FROM provider_part_project
                 LEFT JOIN part p ON provider_part_project.part_id = p.part_id
        WHERE p.color = 'Красный'
    )
);
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
# 2 Получить полную информацию обо всех проектах в Лондоне.
SELECT *
FROM project
WHERE city = 'Лондон';
# 9 Получить номера деталей, поставляемых поставщиком в Лондоне.
SELECT DISTINCT part_id
FROM provider_part_project AS ppp
         LEFT JOIN project AS p ON ppp.project_id = p.project_id
WHERE p.city = 'Псков';
# 13 Получить номера проектов, обеспечиваемых по крайней мере одним поставщиком не из того же города.
SELECT DISTINCT p2.project_id
FROM provider_part_project AS ppp
         LEFT JOIN provider AS p ON ppp.provider_id = p.provider_id
         LEFT JOIN project AS p2 ON ppp.project_id = p2.project_id
WHERE p.city != p2.city;
# 17 Для каждой детали, поставляемой для проекта, получить номер детали, номер проекта и соответствующее общее количество.
SELECT part_id, project_id, sum
FROM provider_part_project;
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
# 18 Получить номера деталей, поставляемых для некоторого проекта со средним количеством больше 320.
SELECT part_id
FROM provider_part_project
GROUP BY part_id
HAVING avg(sum) > 320;
