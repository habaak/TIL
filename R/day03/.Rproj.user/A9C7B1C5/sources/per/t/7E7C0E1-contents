library('ggplot2')
mpg
head(mpg)
View(mpg)
dim(mpg) #몇 행 몇 열
str(mpg) #데이터 속성 출력
summary(mpg) #요약 통계량 - 최소값, 1/4지점 값,2/4지점 값,평균, 3/4지점 값,최대값

tf <- data.frame(x=c(1:5),y=c(6:10))
tfc<-tf
colnames(tfc) <- c('col1','col2')
tfc
#tfc - rename()을 사용하기 위한 라이브러리
install.packages('dplyr')

library(dplyr)
tfc <- rename(tfc, '6-10'=1-5)
tfc

mpg2<-rename(mpg,'city'=cty)
mpg2
mpg2<-rename(mpg2,'highway'=hwy)
aggregate(city~manufacturer,mpg2,mean)

mpg2$total <- (mpg2$city+mpg2$highway)/2
hist(mpg2$total)
View(mpg2)
mpg2$test <-ifelse(mpg2$total>=20, 'PASS', 'FAIL')

table(mpg$test) #특정 columm을 기준으로 count
class(table(mpg$test))
as.vector(table(mpg$test)) #중요 java에서는 vector로 처리하는게 유용함

qplot(mpg$test)

