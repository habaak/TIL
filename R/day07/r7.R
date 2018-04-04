library(plotly)
library(ggplot2)
library(htmlwidgets)

p <- ggplot(data=mpg,aes(
  x=displ,y=hwy,col=drv
))+geom_point()
pp<-ggplotly(p)

saveWidget(pp,file='mpg2.html')
