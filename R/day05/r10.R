library(ggplot2)
g1 <- function(){
  library(ggplot2);
  jpeg(filename = "c:\\rproject\\mychart2.jpg", width = 680,height = 680,quality = 100)
  ggplot(data=midwest, aes(x=poptotal,y=popasian))+geom_point()+xlim(0,500000)+ylim(0,10000)
  
}
dev.off()
g1()

ff <- function(a){
  jpeg("c:\\rproject\\mychart3.jpg")
  p <- qplot(1:20, 1:20)
  print(p)
  dev.off()
}
