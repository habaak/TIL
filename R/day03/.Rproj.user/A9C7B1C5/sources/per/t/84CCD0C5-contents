install.packages('googleVis')
library('googleVis')
head(Fruits)
banana <- Fruits[Fruits$Fruit=='Bananas',]
result <- sum(banana$Sales)
# 그룹화를 시켜야 편하다
# 그룹화 함수
# aggregate(그룹을 지을 내용,데이터,함수)
df1 <- aggregate(Sales~Fruit,Fruits,sum)
df1
df2 <- aggregate(Profit~Year,Fruits,mean)
df2
df3 <- aggregate(Sales~Location+Year,Fruits,sum)
df3
#apply(데이터, 행(1)/열(2), 함수)
df4 <- Fruits[,c(4:6)]
df4
apply(df4,2,sum)

# 문제1. Fruits 데이터를 기반으로 년도별 월별 Sales와 Profit의 합을 구하시오
Year <- substr(Fruits$Date,3,2)
as.Date(Fruits$Date,format='%m / %d / %Y');
substr(as.character(Fruits$Date),6,2)
date <- as.character(Fruits$Date)
date <- strsplit(date,"-")
date
substr()
date <- Fruits$Date
year <- substr(date,1,4)
month <- substr(date,6,7)
# 문제 2. (sale-profit)*1.83

# 문제 3. 년별 월 별 세금을 계산한다.(세금은 총 판매액(Sales-Profit)*0.1)
s_sum <- aggregate(Sales~year+month,f2,sum)
p_sum <- aggregate(Profit~year+month,f2,sum)
p_sum
fee<-(s_sum[3]-p_sum[3])*0.1
colnames(fee)='fee'

P3 <- p_sum[,c(1,2)]
cbind(P3,fee)

p3_1 <- aggregate((Sales-Profit)*0.1~Year+month,f2,sum)
colnames(p3_1) <- c('YEAR','MONTH', 'FEE')
p3_1

