drop table provider;
drop table part;
drop table project;
drop table provider_part_project;

create table provider
(
    ProviderID      VARCHAR(2) primary key,
    ProviderName   VARCHAR(10),
    Status int,
    City  VARCHAR(10)
)CHARACTER SET utf8
 COLLATE utf8_unicode_ci;;
create table part
(
    partID      VARCHAR(2) primary key,
    partName   VARCHAR(10),
    Color   VARCHAR(10),
    Size INT,
    City  VARCHAR(10)
)CHARACTER SET utf8
 COLLATE utf8_unicode_ci;;
create table project
(
    ProjectID    VARCHAR(3) primary key,
    ProjectName VARCHAR(4),
    City VARCHAR(10)
)CHARACTER SET utf8
 COLLATE utf8_unicode_ci;;
create table provider_part_project
(
    ProviderID  VARCHAR(2),
    partID  VARCHAR(2),
    ProjectID VARCHAR(3),
    S  int,
    primary key (ProviderID, partID, ProjectID)
)CHARACTER SET utf8
 COLLATE utf8_unicode_ci;;

insert into provider (ProviderID, ProviderName, Status, City)
values ('П1', 'Петров', 20, 'Москва'),
       ('П2', 'Синицин', 10, 'Таллинн'),
       ('П3', 'Федоров', 30, 'Таллинн'),
       ('П4', 'Чаянов', 20, 'Минск'),
       ('П5', 'Крюков', 30, 'Киев');
insert into part (partID, partName, Color, Size, City)
values ('Д1', 'Болт', 'Красный', 12, 'Москва'),
       ('Д2', 'Гайка', 'Зелёная', 17, 'Минск'),
       ('Д3', 'Диск', 'Черный', 17, 'Вильнюс'),
       ('Д4', 'Диск', 'Черный', 14, 'Москва'),
       ('Д5', 'Корпус', 'Красный', 12, 'Минск'),
       ('Д6', 'Крышки', 'Красный', 19, 'Москва');
insert into project (ProjectID, ProjectName, City)
values ('ПР1', 'ИПР1', 'Минск'),
       ('ПР2', 'ИПР2', 'Таллинн'),
       ('ПР3', 'ИПР3', 'Псков'),
       ('ПР4', 'ИПР4', 'Псков'),
       ('ПР5', 'ИПР5', 'Москва'),
       ('ПР6', 'ИПР6', 'Саратов'),
       ('ПР7', 'ИПР7', 'Москва');
insert into provider_part_project (ProviderID, partID, ProjectID, S)
values ('П1', 'Д1', 'ПР1', 200),
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
select distinct Color
from part p
         left join provider_part_project ppp on p.partID = ppp.partID
where ppp.partID = 'П1';
# 23 Получить номера поставщиков, поставляющих по крайней мере одну деталь, поставляемую по крайней мере одним поставщиком, который поставляет по крайней мере одну красную деталь.

# 31 Получить номера поставщиков, поставляющих одну и ту же деталь для всех проектов.
select t2.producer, count(*)
from (
         select t.provider producer, t.part part
         from (
                  select ProjectID project, ProviderID provider, partID part
                  from provider_part_project ppp
                  group by project, provider, part
              ) t
         group by t.provider, t.part, t.part
     ) t2
group by t2.producer
having count(*) = 6;
# 2 Получить полную информацию обо всех проектах в Лондоне.
select *
from project
where City = 'Лондон';
# 9 Получить номера деталей, поставляемых поставщиком в Лондоне.
select partID
from part
where City = 'Лондон';
# 13 Получить номера проектов, обеспечиваемых по крайней мере одним поставщиком не из того же города.
select t.project
from (select ppp.ProviderID project, p.City sity, count(*) cnt
      from provider p
               left join provider_part_project ppp on p.ProviderID = ppp.ProviderID
      group by ppp.ProjectID, p.City) t
group by project
having count(*) > 1;
# 17 Для каждой детали, поставляемой для проекта, получить номер детали, номер проекта и соответствующее общее количество. ??? XD
select partID, ProjectID, S from provider_part_project;
# 36 Получить все пары номеров поставщиков, скажем, Пx и Пy, такие, что оба эти поставщика поставляют в точности одно и то же множество деталей.
select t.ProviderID, t2.ProviderID
from (
         select ProviderID, SUM(t.S) S
         from provider_part_project t
         group by ProviderID
     ) t,
     (
         select ProviderID, SUM(t.S) S
         from provider_part_project t
         group by ProviderID
     ) t2
where t2.S = t.S
  and t.ProviderID <> t2.ProviderID;
# 27 Получить номера поставщиков, поставляющих деталь Д1 для некоторого проекта в количестве, большем среднего количества деталей Д1 в поставках для этого проекта.

# 18 Получить номера деталей, поставляемых для некоторого проекта со средним количеством больше 320. ????
select partID
from provider_part_project
group by partID
having avg(S) > 320;
