library(rJava);library(RJDBC);library(DBI)
library('dplyr'); library(ggplot2)
drvName = 'oracle.jdbc.driver.OracleDriver'
id = 'ruser'
pwd = '111111'
url = 'jdbc:oracle:thin:@localhost:1521:XE'


# 1. Driver Loading
drv = JDBC(driverClass = drvName, classPath = 'c:\\java_hive_lib\\ojdbc6.jar')

# 2. Connection
conn = dbConnect(drv,url,id,pwd)

#3. Statement
sqlstr <- 'SELECT MONTHS_BETWEEN (sysdate, hiredate) AS MON, sal AS SAL FROM EMP';

#4. ResultSet
emp <- dbGetQuery(conn,sqlstr)
empgraph <- ggplot(data =emp, aes(x = emp$MON, y = emp$SAL))+ geom_line()
print(empgraph)
#5. close
dbDisconnect(conn)