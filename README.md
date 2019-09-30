# fiasloader

Скачивает базу адресов ФИАС с сайта ФНС (загрузка rar с XML через SOAP) и загружает в БД.

## Принцип работы:

0. До начала работы нужно разово прогнать скрипты из `./db`, проверить наличие кодов АТГ в промежуточной базе.
Подключение к промежуточной базе задается блоком параметров `spring.datasource`.
Все параметры можно переопределить при запуске, параметры задаются в таком случае заглавными буквами, с подчёркиванием вместо точек.
1. С периодичностью `download.period` загружает большой файл с базой ФИАС с сайта ФНС (если иное не установлено параметром `download.need`).
Загрузка в папку `download.tmp=tmp`, готовый архив в папке `download.work=work`.
2. С периодичностью `work.period` распаковывает и обрабатывает архив (если иное не указано параметром `upack.need`) в папку `unpack.dir=unpack`.
После распаковки и обработки файлы перемещаются в `work.archDir=archive`.
3. Возможно выключить распаковки и файлы переместить в папку `unpack.dir=unpack`, в этом случае перемещены не будут.
4. При распаковке и обработке файлов используется фильтр по частям имени файла `work.fileMasks=AS_ADDROBJ,AS_DEL_ADDROBJ,AS_SOCRBASE`.
При парсинге учитывается уровень объектов базы (не выше 6), загружаютс актуальные записи.
5. После испорта базы ФИАС происходит сопоставление загруженных регионов регионам и кодам ATG, (пере)создание таблицы с городами для репозитория ATG.
6. После этого приложение работает в режиме сервиса и ожидает запросов на выгрузку CSV-файлов в формате репозитория ATG:
```
SOAP http://localhost:8080/export/cities[?area=<area_id>]
```

## Конгфигурация (application.yml)
Обозначение: конфигурация
```
ParamA:
  ParamB:
    ParamC: value
```
равноценна
```
ParamA.ParamB.ParamC: value
```
### Загрузка (DownloadFilesSсheduler).
По расписанию (по ум.-раз в час).
download:
  period: 3600000 - период.
  need: true - признак необходимости загрузки.
  tmp: tmp - папка для загружаемых файлов (временная).
  dir: work - папка для загруженных файлов (если не задана равна ${work.dir}).
    Нужна всегда при скачивании!
    Если не нужна- в папку unpack.dir поместить XML или в папку work.dir поместить архив.

### Обработка (ProceedFilesSсheduler).
По расписанию (по ум.-раз в день).
work.period: 86400000 - период.

Этот этап включает в себя распаковку файлов:
upack:
  need: true - нужна ли распаковка архива из workDir в unpackDir. Если false- приложение заканчивает работу после обработки.
  dir: unpack - папка для разархивированных файлов.

Потом происходит сама обработка:
work:
  period: 86400000 - период обработки.
  archDir: archive - папка для обработанных файлов.
  dir: work - папка для обработки файлов.
  fileMasks: AS_ADDROBJ,AS_DEL_ADDROBJ,AS_SOCRBASE - какие файлы извлекать (части имен файлов)

#### Размеры пакетов.
batch_size.xml: 10000 - размер пакета при обработке XML.
batch_size.sql: 1000 - размер пакетапри вставке в таблицу, в рамках одного пакета XML.

#### Обработки части AddrObj:
Загрузка только с ACTSTATUS=1 (не настраивается).
addrobj.frombatchn: 0
addrobj.tobatchn: -1 - по умолчанию до конца файла.
Если файлов такого типа несколько - праметры применимы к каждому файлу.

### БД
До начала использования выполнить `1.sql`.
```
spring:
  datasource:
    url: jdbc:oracle:thin:@//t2ru-ds-test-02:2521/TELE2
    username: C##USER
    password: Password123
    driver: oracle.jdbc.driver.OracleDriver (должен быть в classPath)
```
Ещё варианты
```
  datasource:
    driver: org.postgresql.Driver
    url:  jdbc:postgresql:gistimer?stringtype=unspecified
    username: postgres
    password: 222
  datasource:
    driver: org.postgresql.Driver
    url: jdbc:postgresql://172.16.0.203/fias?user=postgres&password=masterkey
  datasource:
    driver: org.firebirdsql.jdbc.FBDriver
    url: jdbc:firebirdsql:172.16.0.203:/opt/bases/fias_tmp.fdb
    username: SYSDBA
    password: masterkey
    encoding: WIN1251
```

Варианты включённых артефактов и драйверов:
```
org.postgresql:postgresql:42.2.2 -> org.postgresql.Driver
org.firebirdsql.jdbc:jaybird-jdk18:3.0.4 -> org.firebirdsql.jdbc.FBDriver
com.oracle.ojdbc:ojdbc8:19.3.0.0 -> oracle.jdbc.driver.OracleDriver
```

## Запуск
### До запуска
1. Создать объекты из `1.sql`;
2. Перенести коды регионов ATG (есть значения в `1.sql`);
3. ???? Придумать, как синхронизировать эти коды с ADDROBJ
4. ???? Придумать, как правильно обновлять данные в репозитории ATG.

### Запуск из Idea
В конфигурации fiasLoader[bootRun] проверить:
```
Gradle Project: fiasloader
Tasks: bootRun
```
Добавить поштучно Enviroment variables, должна получиться строка:
```
DOWNLOAD_NEED=false;WORK_ARCHDIR=x:\\fias\\archive;WORK_DIR=x:\\fias\\work;UNPACK_DIR=x:\\fias\\unpack;DOWNLOAD_DIR=x:\\fias\\work;DOWNLOAD_TMP=x:\\fias\\tmp
```
### Запуск из скрипта (DEV)
```
[<PARAM>=<value> ] bash gradlew bootRun
```
<PARAM> - переменные из `application.yml`, написанные капсом и содержащие `_` вместо точки.
Пример
```
 WORK_DIR=x:\\fias\\work WORK_ARCHDIR=x:\\fias\\archive DOWNLOAD_NEED=false UNPACK_NEED=false bash gradlew bootRun
```
### Запуск из bash.
Локально:
```
export DOWNLOAD_NEED=false
export DOWNLOAD_TMP=x:\\fias\\tmp
export DOWNLOAD_DIR=x:\\fias\\work
export UNPACK_NEED=false
export UNPACK_DIR=x:\\fias\\unpack
export WORK_DIR=x:\\fias\\work
export WORK_ARCHDIR=x:\\fias\\archive
export ADDROBJ_FROMBATCHN=973
export DATABASE_URL=jdbc:oracle:thin:@//<ip>:<port>/<sid>
export DATABASE_DBUSER=<Scheme>
export DATABASE_PASSWORD=<Password>
export DATABASE_DRIVER=oracle.jdbc.driver.OracleDriver
java -jar  fiasloader-0.3.0.jar
```
Для контейнера - подключение по имени сервиса, а не по хосту, добавить параметр прокси для загрузки со сторонних сайтов.

### Выгрузка справочника городов в формате репозитория ATG
Есть эндпоинт с опциональным параметром (значение параметра может быть пустым или параметр можно не указывать)
```
SOAP http://localhost:8080/export/cities?area=<area_id>
```
#### Для контейнера
```
http://<test_stand_ip>:9080/export/cities?area=<area_id>
```
В расшаренной папке изменить докер-файл `docker-compose.yml`, добавить маршрутизацию порта: ```
- "9080:8080"
```
#### Справка
Получение README.md:
```
SOAP http://localhost:8080/readme
```
## Вспомогательные скрипты
### Получение полного имени объекта.
По `AOGUID` объекта с уровнем 6:
```
select
  listagg(a.SHORTNAME||' '||a.FORMALNAME, ', ') within group (order by a.AOLEVEL desc) TheFullName
from FIAS_ADDROBJ a
connect by prior a.PARENTGUID=a.AOGUID
start with a.AOGUID=:AOGUID
```
### Подготовленные данные после загрузки справочника можно получить так:

```
create table FIAS_ATG_CITIES as
 select
   'NEW' as ID,a.OFFNAME as name,
   (select min(ATG_CODE) keep (dense_rank LAST order by level) from FIAS_ADDROBJ a3
           connect by prior a3.PARENTGUID=a3.AOGUID
           start with a3.AOID=a.AOID) as AREAS,a.AOGUID as fiasCode,
   a.SHORTNAME as cityType,a.FORMALNAME as searchableName,
   (select listagg(a2.SHORTNAME||' '||a2.FORMALNAME, ', ') within group (order by decode(connect_by_root aoid,aoid,0,a2.AOLEVEL))
     from FIAS_ADDROBJ a2
     connect by prior a2.PARENTGUID=a2.AOGUID
     start with a2.AOID=a.AOID
   ) as fullName,
   a.AOID
 from FIAS_ADDROBJ a
 join FIAS_SOCR s on (s.CODE=a.SHORTNAME and s.SOCR_LEVEL=a.AOLEVEL
   and s.KOD_T_ST in  (112,602,6537,9133,103,401,605,606,736,9136,6541,623,751,9151,655,630,755,9155,6568,631,756,9156,6570,633))
```