library(ggplot2)
jpeg(filename = "c:\\rproject\\mychart.jpg", width = 680,height = 680,quality = 100)
ggplot(data=midwest, aes(x=poptotal,y=popasian))+geom_point()+xlim(0,500000)+ylim(0,10000)
dev.off()
