v1<-c(70,80,90,100);
names(v1) <- c('ko','en','si','ma');

vv <- mean(v1[2:4]);
result <- print(vv)
mean(v1[-(2:3)])

print(NROW(v1));

vv2 <- v1[c(1,4)]
vv2
vv2 <- v1[c(-3,-1)]
vv2
v1[names(v1)[2]]
