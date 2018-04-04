install.packages("rvest")
library(rvest)
library(wordcloud)
library(KoNLP)

wc<-function(arg,query="지훈"){
  
  pagen = c(11,21,31,41,51,61,71,81,91,100)
  url1<-"https://search.naver.com/search.naver?&where=news&query="
  url2<-"&sm=tab_pge&sort=0&photo=0&field=0&reporter_article=&pd=0&ds=&de=&docid=&nso=so:r,p:all,a:all&mynews=0&cluster_rank=28&start="
  url<-paste0(url1,query,url2)
  txt<-c()
  for (i in pagen){
    newurl<-paste0(url,i)
    a<-read_html(newurl)
    a<-html_nodes(a,css=".type01")
    a<-html_text(a)
    txt<-append(txt,a)
    print(i)
  }
  
  word<-extractNoun(txt)
  word<-unlist(word)
  tb<-table(word)
  tb<-sort(tb,decreasing = T)
  tb<-tb[1:100]
  
  pal <- brewer.pal(8,"Dark2")
  
  if (arg==1){
    print(wordcloud(names(tb),tb,col=pal,random.order = F))
  }else{
    jpeg("wc.jpg")
    a<-wordcloud(names(tb),tb,col=pal,random.order = F)
    print(a)
    dev.off()
  }
  
}
wc(1,"박준하")
