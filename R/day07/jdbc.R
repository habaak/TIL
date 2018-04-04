library(sqldf)
library(ggplot2)
str(iris)
class(iris)
mydata<-sqldf('SELECT * FROM iris WHERE species = "setosa"')
mydata2 <- iris[iris$Species=='setosa',]

mydata3 <- sqldf('select avg("Sepal.Length"),avg("Sepal.Width"),avg("Petal.Length"),avg("Petal.Width") from iris')
mydata4 <- colMeans(iris[,c(1:4)])


mydata5 <- iris %>%
  group_by(Species) %>%
  summarise(
    Mean.Sepal.Length <- mean( Sepal.Length),
    Mean.Sepal.Width <-mean(Sepal.Width),
    Mean.Petal.Length<-mean(Petal.Length),
    Mean.Petal.Width<-mean(Petal.Width)
  )
class(mydata5) #data.frame

mydata6<-apply(iris[,1:4],2, tapply,INDEX=iris[,5],mean)
class(mydata6) #matrix

ggplot(data=iris,aes=(x='Sepal.Width', y='Petal.Width'))+geom_line()

