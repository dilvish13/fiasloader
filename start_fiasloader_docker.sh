#!/bin/bash
export DOWNLOAD_NEED=false
export DOWNLOAD_TMP=tmp
export DOWNLOAD_DIR=work
export UNPACK_NEED=false
export UNPACK_DIR=unpack
export WORK_FILEMASKS=AS_ADDROBJ,AS_SOCRBASE,AS_DEL_ADDROBJ
export WORK_DIR=work
export WORK_ARCHDIR=archive
export ADDROBJ_FROMBATCHN=0
export SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//db:1521/TELE2
export SPRING_DATASOURCE_USERNAME=C##PROD
export SPRING_DATASOURCE_PASSWORD=Welcome1
export SPRING_DATASOURCE_DRIVER=oracle.jdbc.driver.OracleDriver
java -jar  fiasloader-0.3.0.jar  -Dhttp.proxyHost=t2ru-ds-proxy-01 -Dhttp.proxyPort=80