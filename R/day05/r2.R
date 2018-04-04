library(dplyr)
mpg2 <- mpg[,c('drv','hwy')]
#그룹별 고속도로 연비 평균

result<-mpg %>%
group_by(drv) %>%
  summarise(
    mean(hwy)
  )
result

ggplot(data=result,aes(x=reorder(drv, -`mean(hwy)`),y=`mean(hwy)`))+geom_col()+geom_line()

ggplot(data=mpg,aes(x=hwy))+geom_bar()

result<-mpg %>%
  group_by(manufacturer)%>%
  summarise(
    class="suv",
    mean(cty)
  )
result <-head( arrange(result, desc(`mean(cty)`)) ,5)
result
ggplot(data=result,aes(x=reorder(manufacturer,-`mean(cty)`),y=`mean(cty)`))+geom_col();

ggplot(data = mpg,aes(x=class))+geom_bar()
