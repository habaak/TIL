library(dplyr)
exam <- read.csv('csv_exam.csv')
exam
colnames(exam) <-c('id','name','class','math','english','science')
exam %>% filter(class == 1)
exam[exam$class!=1,]

exam[exam$math >= 70 & exam$english >= 80,]
exam[exam$math %in% c(50:70),]


displ4<-mean(mpg[mpg$displ<=4,]$hwy)
displ5<-mean(mpg[mpg$displ>=5,]$hwy)

mean(mpg[mpg$manufacturer %in% 'audi',]$cty)
mean(mpg[mpg$manufacturer %in% 'toyota',]$cty)

chev<-mean(mpg[mpg$manufacturer %in% 'chevrolet',]$hwy)
ford<-mean(mpg[mpg$manufacturer %in% 'ford',]$hwy)
honda<-mean(mpg[mpg$manufacturer %in% 'honda',]$hwy)

mean(mpg[mpg$manufacturer %in% c('honda', 'ford','chevolet')]$hwy)

mean(chev,ford,honda)

aggregate(hwy~manufacturer,mpg,mean)
temp <- aggregate(hwy~displ <= 4, mpg,mean)
qplot(temp$hwy,x=c('5<=','4>='))
