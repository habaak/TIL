library(ggplot2)
#1. 판 만들기 #2. 그래프 그리기
ggplot(data=mpg,aes(x=mpg$displ, y=mpg$hwy))+geom_point()+geom_line()+ylim(10,50)+xlim(2,8)

ggplot(data=mpg,aes(x=mpg$cty,y=mpg$hwy))+geom_point()

ggplot(data=midwest,aes(x=midwest$poptotal,y=popasian))+geom_point()+xlim(0,500000)+ylim(0,10000)

fname <- 'c:\rproject\mygraph.png'
savePlot(filename = fname,type = 'png')
dev.off()
