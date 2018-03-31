name <- c('lee','kim','park','jang','hong');
age <- c(10,20,30,40,50);
weight <- c(40,50,60,70,80);
df <- data.frame(name,age,weight);
df
dim(df);
nrow(df);
ncol(df);
typeof(df);
df[1,]
mean(df[c(1,2),2])
df[c(1:3),]
df2 <- df[c(1:3),];
mean(df2[,2]);
df2
colMeans(df2[,c('age','weight')]);

max(df$age);
c <- df$age;

which(c == 50);
whi
mean(df$age[c(-which.max(df$age),-which.min(df$age))])

sort(c);
maean();

df$height <- c(180,170,160,150,155);

df
bmi<-function(x ,y){
  x / (y^2)*10000;
}
bmi(df$weight,df$height)
bmi(62,172)


df2 <- df[df$weight>=50,]
df2[which(df2$height== max(df2$height)),]
df2[df2$height== max(df2$height),]

df[df$weight>=50 & df$weight <=60,]
df[c(TRUE,TRUE,TRUE,FALSE,FALSE),]

ww <- df$weight
hh <- df$height
