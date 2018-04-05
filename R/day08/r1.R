r1 <- function(a,b){
  v1 <- a*10 + b*0.34;
  return (v1);
}

r2<-function(){
  v2 <- c(1:10)
  return (v2)
}
r3 <-function(){
  time <- c('6시','7시','8시','9시','10시')
  line2 <- c(1000,2000,3000,4000,5000)
  line3 <- c(1200,2200,3200,4500,5100)
  line4 <- c(1400,2400,3100,4200,6000)
  v3<- data.frame(time,line2,line3,line4)
  return(v3)
}

