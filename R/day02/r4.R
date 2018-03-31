library(ggplot2)
data <- c(10,10,20,20,30,30)
qplot(data)
mpg

qplot(data=mpg,y=hwy, x=drv)
qplot(data = mpg,x = drv, y = hwy, geom = "line")
qplot(data = mpg,x = drv, y = hwy, geom = "boxplot")
qplot(data = mpg,x = drv, y = hwy, geom = "boxplot", colour = drv)
