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

length(v1)
NROW(v1)
nrow(v1)

c1 <- c(1:5);
c2 <- c(6:10);
cs <- union(c1,c2)
cs2 <- setequal(c1,c2)

cs %in% 4;

5+cs

res <- c(1:3) %in% cs;
print(length(res));
c3 <- seq(0,1000,5);

m1 <- matrix(c(1,2,3,4,5,6,7,8,9), nrow = 3);
m2 <- matrix(c(1,2,3,4,5,6,7,8,9), ncol = 3, byrow = TRUE);

m1
m2

colnames(m1) <- c('c1','c2','c3');
rownames(m1) <- c('r1','r2','r3');
m1
m1[1,2]


m1[c(1:2),2]
m1['r2',]

matrix(c())