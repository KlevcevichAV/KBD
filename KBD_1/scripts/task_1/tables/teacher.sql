CREATE TABLE teacher
(
    personal_number VARCHAR(4) PRIMARY KEY,
    last_name       VARCHAR(20),
    position        VARCHAR(20),
    chair           VARCHAR(20),
    specialty       VARCHAR(50),
    home_phone      INT
) CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

INSERT INTO teacher (personal_number, last_name, position, chair, specialty, home_phone)
VALUES ('221Л', 'Фролов', 'Доцент', 'ЭВМ', 'АСОИ, ЭВМ', 487),
       ('222Л', 'Костин', 'Доцент', 'ЭВМ', 'ЭВМ', 543),
       ('225Л', 'Бойко', 'Профессор', 'АСУ', 'АСОИ, ЭВМ', 112),
       ('430Л', 'Глазов', 'Ассистент', 'ТФ', 'СД', 421),
       ('110Л', 'Петров', 'Ассистент', 'Экономики', 'Международная экономика', 324);
