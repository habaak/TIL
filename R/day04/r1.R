tf <- data.frame(
  id=(1:5),
  test=c(86:90)
)

tf2 <- data.frame(
  id=(6:10),
  test=c(91:95)
)

total <- rbind(tf,tf2)
total


total$test2 <-c(90:99)
total

# avg colume에 평균을 입력하시오
total$avg<-rowMeans(total[,c(2,3)])
total

# 평균이 높은 탑3
library(dplyr)
total %>% arrange(desc(avg)) %>% head(3)

# avg가 평균 이상인 학생
total[total$avg>=mean(total$avg),]

##############
#rename 