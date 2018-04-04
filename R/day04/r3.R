st <- read.csv('csv_exam.csv',header = F)
st
colnames(st) <- c('id','class','ko','en','ma')
table(is.na(st$class))
mean(st$ko,na.rm=T)
#학생 정보를 조회 하시오
# 단, id,class,ko 결측치가 있는 학생은 빼시오

(is.na(st))

st[!is.na(st$ko)&!is.na(st$class),c('id','class','ko')]
na.omit(st[,c('id','class','ko')]) #결측치를 다 빼버

#국어,영어,수학 성적의 평균을 구하여 vector를 만든다.
avg <- colMeans( st[,c('ko','en','ma')] ,na.rm = T)

# NA 값을 모두 0으로 바꾸시오

st$class <-ifelse(is.na(st$class),0,st$class)
st$ko <-ifelse(is.na(st$ko),0,st$ko)
st$en <-ifelse(is.na(st$en),0,st$en)
st$ma <-ifelse(is.na(st$ma),0,st$ma)


boxplot(st$ko)
hist(st$ko)

