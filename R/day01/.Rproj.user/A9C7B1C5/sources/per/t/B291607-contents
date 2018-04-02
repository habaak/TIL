score <- matrix(c(80,80,90,99,90,99,78,89,70,78,82,78,100,72,78,90), nrow = 4)
rownames(score) <- c('kim','lee','hong','jang');
colnames(score) <- c('ko','en','si','ma');
df <- score;
df

df2<-cbind(df,'avg'=apply(df,1,mean));
df2<-cbind(df2,'sum'=apply(df,1,sum));
df3<-rbind(df2,'avg'=apply(df2,2,mean));
df3;
df3<-round(df3,1);
df3


