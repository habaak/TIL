library(KoNLP)
library(dplyr)
library(stringr)
library(wordcloud)
library(RColorBrewer)
useNIADic()
txt1<-readLines('http://www.hanatour.com')
txt2<-readLines('http://www.modetourda.com')
txt3<-readLines('http://www.lottetour.com')
txt <-c(txt1,txt2,txt3)
head(txt)
#txt<-str_replace(txt,'\\W',' ')
#txt<-str_replace(txt,'[0-9]',' ')
#txt<-str_replace(txt,'[a-z]',' ')
#txt<-str_replace(txt,'[A-Z]',' ')
txt<-gsub('\\W','',txt)
txt<-gsub('[0-9]','',txt)
txt<-gsub('[a-z]','',txt)
txt<-gsub('[A-Z]','',txt)


noun <- extractNoun(txt)

noun

vc <- unlist(noun)
vc
wc<-table(vc)

wf<-as.data.frame(wc, stringsAsFactors = F)
head(wf)
wf<-filter(wf,nchar(vc)>=2)

head(wf[order(wf$Freq,decreasing = T),],30)
pal<-brewer.pal(10,'Set1')
set.seed(1234);

jpeg(filename = "wordcloud.jpg",width = 680,height = 680,quality = 100)
p = wordcloud(words = wf$vc,
          freq = wf$Freq,
          min.freq = 2,
          max.words = 200,
          random.order = F,
          colors = pal,
          rot.per = 0.2,
          scale=c(10,1)
          )

print(p)
dev.off()
