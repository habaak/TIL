#Data Load : data frame()
#wifi 변수
#wifi / KT / LG / SKT 
#이미지가 각각 저장 된다.
library(readr)
library()
library(RgoogleMaps)



myCenter= c(37.550784, 126.991129)
mymap <- GetMap(
  center= myCenter,
  zoom = 11,
  maptype = 'load',
  #format = 'roadmap',
  destfile = 'mymap.jpg'
)

win.graph()

seoul <-rename(seoul, 
       company = `설치기관(회사)`,
        lat = `설치위치(X좌표)`,
        lon = `설치위치(Y좌표)`,
        dst = 구명,
        type = 유형,
        point = 지역명
       )
head(seoul)


if( seoul[,6] ){
  mydata <- data.frame(
    seoul$lat
    seoul$lon
  )
}else if(){
  mydata <- data.frame(
    seoul$lat
    seoul$lon
  )
}else if(){
  mydata <- data.frame(
    seoul$lat
    seoul$lon
  )
}
  
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






wifi<-function(arg){
  seoul <- read.csv("nn.csv")
  colnames(seoul)=c("a","b","c","lat","lon","company")
  center=c(mean(seoul$lon),mean(seoul$lat))
  if (arg==1){
    a=seoul[seoul$company=="KT",c(4,5,6)]
    lat= a$lat
    lon =a$lon
  }else if (arg==2){
    a=seoul[seoul$company=="LGU+",c(4,5,6)]
    lat= a$lat
    lon =a$lon
  }else if (arg==3){
    a=seoul[seoul$company=="SKT",c(4,5,6)]
    lat= a$lat
    lon =a$lon
  }else{
    a=seoul[seoul$company!=C("KT","LGU+","SKT"),c(4,5,6)]
    lat= a$lat
    lon =a$lon
  }
  map<-GetMap(center=center,zoom=11,maptype = "roadmap",destfile = "mymap.jpg")
  PlotOnStaticMap(map,lat=lat,lon=lon,destfile="map_point.jpg",cex=1,pch=19,col=1)
}
