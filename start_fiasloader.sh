#!/bin/bash
export DOWNLOAD_NEED=false
export DOWNLOAD_TMP=x:\\fias\\tmp
export DOWNLOAD_DIR=x:\\fias\\work
export UNPACK_NEED=false
export UNPACK_DIR=x:\\fias\\unpack
export WORK_FILEMASKS=AS_ADDROBJ,AS_SOCRBASE,AS_DEL_ADDROBJ
export WORK_DIR=x:\\fias\\work
export WORK_ARCHDIR=x:\\fias\\archive
export WORK_MODIFY_NEED=true
export ADDROBJ_FROMBATCHN=0
export SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//t2ru-ds-test-02:2521/TELE2
export SPRING_DATASOURCE_USERNAME=C##PROD
export SPRING_DATASOURCE_PASSWORD=Welcome1
export SPRING_DATASOURCE_DRIVER=oracle.jdbc.driver.OracleDriver
java -jar  c:/work/git/openSource/fiasloader/build/libs/fiasloader-0.3.0.jar