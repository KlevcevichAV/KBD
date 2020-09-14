CREATE TABLE project
(
    project_id   VARCHAR(3) PRIMARY KEY,
    project_name VARCHAR(4),
    city         VARCHAR(10)
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

INSERT INTO project (project_id, project_name, city)
VALUES ('ПР1', 'ИПР1', 'Минск'),
       ('ПР2', 'ИПР2', 'Таллинн'),
       ('ПР3', 'ИПР3', 'Псков'),
       ('ПР4', 'ИПР4', 'Псков'),
       ('ПР5', 'ИПР5', 'Москва'),
       ('ПР6', 'ИПР6', 'Саратов'),
       ('ПР7', 'ИПР7', 'Москва');
