create table FIAS_SOCR (
CODE varchar2(200) not null,
SOCR_LEVEL number not null,
NAME varchar2(200) not null,
KOD_T_ST varchar2(10) PRIMARY KEY
);/

create table FIAS_ADDROBJ (
AOGUID varchar2(200) not null,
AOID varchar2(200) not null,
AOLEVEL number not null,
FORMALNAME varchar2(2000),
OFFNAME varchar2(2000),
PARENTGUID varchar2(200),
REGIONCODE varchar2(200),
SHORTNAME varchar2(200),
ATG_CODE varchar2(20)
);/

create index FIAS_ADDROBJ_AOID on FIAS_ADDROBJ (AOID);
create index FIAS_ADDROBJ_AOGUID on FIAS_ADDROBJ (AOGUID);
create index FIAS_ADDROBJ_AOPGUID on FIAS_ADDROBJ (PARENTGUID);
create index FIAS_SOCR_CODE on FIAS_SOCR (CODE);


create table FIAS_ATG_AREAS (
code varchar2(10) not null,
name varchar2(400) not null
);/

create or replace procedure recreateAtgCitiesTable is
  needDrop number;
  sqlDrop varchar2(200) := 'drop table FIAS_ATG_CITIES';
  sqlCreate varchar2(4000) :=
'create table FIAS_ATG_CITIES as'||
' select'||
'   ''NEW'' as ID,a.OFFNAME as name,'||
'   (select min(ATG_CODE) keep (dense_rank LAST order by level) from FIAS_ADDROBJ a3'||
'           connect by prior a3.PARENTGUID=a3.AOGUID'||
'           start with a3.AOID=a.AOID) as AREAS,a.AOGUID as fiasCode,'||
'   a.SHORTNAME as cityType,a.FORMALNAME as searchableName,'||
'   (select listagg(a2.SHORTNAME||decode(substr(a2.SHORTNAME,length(a2.SHORTNAME)-1,1),''.'','''',''.'')||'' ''||a2.FORMALNAME, '', '')'||
'       within group (order by decode(connect_by_root aoid,aoid,0,a2.AOLEVEL))'||
'     from FIAS_ADDROBJ a2'||
'     connect by prior a2.PARENTGUID=a2.AOGUID'||
'     start with a2.AOID=a.AOID'||
'   ) as fullName,'||
'   a.AOID'||
' from FIAS_ADDROBJ a'||
' join FIAS_SOCR s on (s.CODE=a.SHORTNAME and s.SOCR_LEVEL=a.AOLEVEL'||
'   and s.KOD_T_ST in (103,111,112,401,402,417,418,419,605,636,606,621,624,630,651))';
begin
  select count(1) into needDrop from USER_TABLES where TABLE_NAME = 'FIAS_ATG_CITIES';
  if needDrop = 1 then
     EXECUTE IMMEDIATE sqlDrop;
  end if;
EXECUTE IMMEDIATE sqlCreate;
end;
/

create or replace procedure updateAtgAreasCodes is
begin
  for j in (
    select o.offname, a.name, a.code, o.aoid, o.aoguid
    from FIAS_ATG_AREAS a
      full join FIAS_ADDROBJ o on (lower(a.name) like lower('%' || o.offname || '%'))
    where o.AOLEVEL = 1
    ) loop
      update FIAS_ADDROBJ set ATG_CODE=j.code where aoid = j.aoid;
    end loop;
  commit;
end;
/

begin
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300001','Алтайский край');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300002','Амурская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300003','Архангельская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300004','Астраханская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300005','Белгородская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300006','Брянская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300007','Владимирская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300008','Волгоградская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300009','Вологодская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300010','Воронежская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300011','Еврейская АО');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300012','Забайкальский край Агинский Бурятский');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300013','Ивановская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300014','Иркутская обл Усть-Ордынский Бурятский');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300015','Кабардино-Балкарская Республика');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300016','Калининградская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300017','Калужская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300018','Камчатский край');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300018','Камчатский край Корякский');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300019','Карачаево-Черкесская Республика');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300020','Кемеровская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300021','Кировская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300022','Костромская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300023','Краснодарский край и Республика Адыгея');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('600001','Красноярский край (Норильск)');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300024','Красноярский край (кроме Норильска)');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300025','Курганская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300026','Курская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300027','Липецкая область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300028','Магаданская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300029','Москва и Московская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('900001','Москва и Московская область Интернет-Магазин');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300030','Мурманская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300031','Ненецкий АО');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300032','Нижегородская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300033','Новгородская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300034','Новосибирская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300035','Омская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300036','Оренбургская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300037','Орловская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300038','Пензенская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300039','Пермский край');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300039','Коми-Пермяцкий');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300040','Приморский край');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300041','Псковская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300042','Республика Алтай');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300043','Республика Башкортостан');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300044','Республика Бурятия');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300045','Республика Дагестан');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300046','Республика Ингушетия');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300047','Республика Калмыкия');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300048','Республика Карелия');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300049','Республика Коми');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300050','Республика Крым и г. Севастополь');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300051','Республика Марий Эл');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300052','Республика Мордовия');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300053','Республика Саха /Якутия/');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300054','Республика Северная Осетия - Алания');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300055','Республика Татарстан');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300056','Республика Тыва');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300057','Республика Хакасия');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300058','Ростовская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300059','Рязанская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300060','Самарская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('400002','Санкт-Петербург и Ленинградская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300061','Саратовская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300062','Сахалинская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300063','Свердловская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300064','Севастополь');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300065','Смоленская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300066','Ставропольский край');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300067','Таймырский (Долгано-Ненецкий)');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300068','Тамбовская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300069','Тверская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300070','Томская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300071','Тульская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300072','Тюменская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300073','Удмуртская Республика');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300074','Ульяновская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300075','Хабаровский край');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300076','Ханты-Мансийский Автономный округ - Югра');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300077','Челябинская область');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300078','Чеченская Республика');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300079','Чувашская Республика -');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300080','Чукотский АО');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300081','Эвенкийский АО');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300082','Ямало-Ненецкий АО');
insert into FIAS_ATG_AREAS (CODE, NAME) values ('300083','Ярославская область');
commit;
end;