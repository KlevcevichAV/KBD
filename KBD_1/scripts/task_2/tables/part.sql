CREATE TABLE part
(
    part_id   VARCHAR(2) PRIMARY KEY,
    part_name VARCHAR(10),
    color     VARCHAR(10),
    size      INT,
    city      VARCHAR(10)
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

INSERT INTO part (part_id, part_name, color, size, city)
VALUES ('Д1', 'Болт', 'Красный', 12, 'Москва'),
       ('Д2', 'Гайка', 'Зелёная', 17, 'Минск'),
       ('Д3', 'Диск', 'Черный', 17, 'Вильнюс'),
       ('Д4', 'Диск', 'Черный', 14, 'Москва'),
       ('Д5', 'Корпус', 'Красный', 12, 'Минск'),
       ('Д6', 'Крышки', 'Красный', 19, 'Москва');
