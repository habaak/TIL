library(ggplot2)
library(dplyr)
mid_west<-midwest
mid_west 
mid_west$young <- mid_west$poptotal-mid_west$popadults
mid_west$youngPercent <-mid_west$young/mid_west$poptotal
mid_west$youngPercent
head(arrange(desc(mid_west$youngPercent)),5)

prob2 <- mid_west %>% group_by(county) %>%
  arrange(desc(youngPercent))%>%
  head(5)

prob2$youngPercent

#3.
mid_west$cat <- if(mid_west$youngPercent>=0.4){
  mid_west$avg='large'
}else if(mid_west$youngPercent>=0.3 & mid_west$youngPercent<0.4){
  mid_west$avg='middle'
}else{
  mid_west$avg='small'
}
mid_west$popjuni <-
  100-(mid_west$popadults/midwest$poptotal)*100


mid_west$grade <- ifelse(mid_west$popjuni>=40, 'large', ifelse(mid_west$popjuni<30, 'small','middle'))

#4.
mid_west$popasianrate <-
  (mid_west$popasian/mid_west$poptotal*100)

head(mid_west[order(mid_west$popasianrate),c('state','county','popasianrate')],10)

