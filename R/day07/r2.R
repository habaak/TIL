library(KoNLP)
library(dplyr)
library(stringr)
library(wordcloud)
library(RColorBrewer)
library(ggplot2)
useNIADic()

#txt1 <- readLines('news.txt')
#txt2 <- readLines('news2.txt')
#txt3 <- readLines('news3.txt')

#txt <- c(txt1,txt2,txt3)
#txt<-gsub('\\W',' ',txt)
#txt<-gsub('[0-9]','',txt)
#txt<-gsub('[a-z]','',txt)
#txt<-gsub('[A-Z]','',txt)
#txt<-gsub('_','',txt)
#txt<-gsub('__','',txt)
#txt<-gsub(' ',' ',txt)
#txt<-gsub('  ','',txt)
#txt<-gsub('들이','',txt)
#txt<-gsub('것한','',txt)
#txt<-gsub('한','',txt)
#txt<-gsub('것','',txt)

#txt <- extractNoun(txt)
#vc <- unlist(txt)
#wc <- table(vc)
#wf <- as.data.frame(wc,stringsAsFactors = F)
#wf<- filter(wf,length(wf$vc)>=2 & nchar(wf$vc)<=5)
#str(wf)
#wf <- head(wf[order(wf$Freq,decreasing = T),],80)
#head(wf)
#pal<-brewer.pal(8,'Set1')
#set.seed(1234);

wc <- function(var){
  #txt1 <- readLines('news.txt')
  #txt2 <- readLines('news2.txt')
  #txt3 <- readLines('news3.txt')
  
  #txt <- c(txt1,txt2,txt3)
  
  txt <- readLines('KakaoTalk_20180403_1426_45_980_group.txt')
  txt<-gsub('\\W',' ',txt)
  txt<-gsub('[0-9]','',txt)
  txt<-gsub('[a-z]','',txt)
  txt<-gsub('[A-Z]','',txt)
  txt<-gsub('_','',txt)
  txt<-gsub('__','',txt)
  txt<-gsub(' ',' ',txt)
  txt<-gsub('  ','',txt)
  txt<-gsub('들이','',txt)
  txt<-gsub('것한','',txt)
  txt<-gsub('한','',txt)
  txt<-gsub('것','',txt)
  
  txt <- extractNoun(txt)
  vc <- unlist(txt)
  wc <- table(vc)
  wf <- as.data.frame(wc,stringsAsFactors = F)
  wf<- filter(wf,length(wf$vc)>=2 & nchar(wf$vc)<=5)
  str(wf)
  wf <- head(wf[order(wf$Freq,decreasing = T),],80)
  
  
  
 if(var==1){
   jpeg(filename = "wc.jpg",width = 680,height = 680,quality = 100)
   head(wf)
   pal<-brewer.pal(8,'Set1')
   set.seed(1234);
   p = wordcloud(words = wf$vc,
                 freq = wf$Freq,
                 min.freq = 1,
                 max.words = 200,
                 random.order = F,
                 colors = pal,
                 rot.per = 0.2,
                 scale=c(10,1)
   )
   
 } else if(var==2){
   jpeg(filename = "wcbar.jpg",width = 680,height = 680,quality = 100)
   order <- head(wf[order(wf$Freq),],30)
   order <- order$vc
   
   p = ggplot(data=wf,aes(x=wf$vc, y=wf$Freq))+ylim(0,40)+geom_col()+coord_flip()+scale_x_discrete(limit = order)+ geom_text(aes(label = Freq), hjust = -0.3)
   
 }
  print(p)
  dev.off()
}

wc(1)
wc()

