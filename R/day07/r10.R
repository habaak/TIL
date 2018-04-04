library(rJava)
library(RJDBC)
library(DBI)

drvName<-'org.apache.hive.jdbc.HiveDriver';
id<-'root';
pwd<-'111111';
url<-'jdbc:hive2://192.168.111.100:10000';

hive_lib<-'c:\\java_hive_lib'

#hive_lib안에 있는 jar들을 사용하겠다 라는 것
.jinit();
.jaddClassPath(dir(hive_lib,full.names = T));
.jclassPath();

drv<-JDBC(driverClass = drvName,
          classPath = 'hive-jdbc-1.0.1.jar')

#2.Connection
conn<-dbConnect(drv,url,id,pwd)

#3.Statement

sqlstr <- 'select * from airline_delay limit 10';

#4.ResultSet

air <- dbGetQuery(conn,sqlstr)

#5.Close
dbDisconnect(conn)