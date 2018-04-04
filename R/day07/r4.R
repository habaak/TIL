
str(changeCode(korpop1))
korpop1 <- rename(
  kormap1,
  pop=총인구_명,
  name=행정구역별_읍면동
)

library(kormaps2014)
library(moonBook2)
library(ggplot2)
theme_set(theme_gray(base_family="NanumGothic"))

ggplot(korpop1,aes(map_id=code,fill=pop))+
  geom_map(map=kormap1,colour="black",size=0.1)+
  expand_limits(x=kormap1$long,y=kormap1$lat)+
  scale_fill_gradientn(colours=c('white','orange','red'))+
  ggtitle("2015년도 시도별 인구분포도")+
  coord_map()