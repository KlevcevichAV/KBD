CREATE TABLE provider
(
    provider_id   VARCHAR(2) PRIMARY KEY,
    provider_name VARCHAR(10),
    status        int,
    city          VARCHAR(10)
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

INSERT INTO provider (provider_id, provider_name, status, city)
VALUES ('П1', 'Петров', 20, 'Москва'),
       ('П2', 'Синицин', 10, 'Таллинн'),
       ('П3', 'Федоров', 30, 'Таллинн'),
       ('П4', 'Чаянов', 20, 'Минск'),
       ('П5', 'Крюков', 30, 'Киев');
