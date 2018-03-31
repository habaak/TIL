mpg2 <- mpg[,c('model','year')]
mpg2
exam
exam[exam$class==2,c('english','math')]

##iris[,!(  names(iris) %in% c('Sepal.Length')  ) ]

sort(exam, x =exam.math)

exam[order( exam$class,exam$math, decreasing = T ),]

exam2 <- exam%>%arrange(class,math)
exam2
exam3 <- exam %>% arrange(desc(math))
head(exam3, 5)

#각 반 별 3과목 평균이 높은사람 순으로 정렬하시오
score <- exam[,c(3:5)]
score

means<-rowMeans(exam[,c(3:5)])
exam4 <- cbind(exam, means)
exam4[order(exam4$class, exam4$means, decreasing = T),]

mpg2<-mpg[,c('hwy','cty')]
mpg2
mpg2<-cbind(mpg2,rowMeans(mpg2))

mpg2<-mpg
mpg2<-mpg2 %>% mutate(total = cty+hwy)%>% mutate(totalavg = total/2)%>% arrange(desc(totalavg)) %>% head(3)

groupby



mpg.grb<-group_by(mpg,year,manufacturer)
summarise(mpg.grb,sum(cyl),sum(hwy))

