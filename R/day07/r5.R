library(RgoogleMaps)
myCenter= c(37.550784, 126.991129)
mymap <- GetMap(
  center= myCenter,
  zoom = 13,
  maptype = 'load',
  #format = 'roadmap',
  destfile = 'mymap.jpg'
)

win.graph()
mydata <- data.frame(
  a=c(1:5),#지명
  lat=c(37.530784,37.530784,37.550184,37.150784,37.530784),
  lon=c(126.991129,126.131111,126.242222,126.122322,126.391121)
    )
p <- PlotOnStaticMap(
  mymap,
  lat = mydata$lat,
  lon = mydata$lon,
  #destfile = 'mymap_point.jpg',
  cex=2,pch=9,col='darkblue'
)

print(p);
dev.off()
