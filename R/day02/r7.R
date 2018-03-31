getcsv <- function(){
  data <- read.csv('csv_exam_3.csv',header = F, stringsAsFactors = F, encoding = 'UTF-8');
  class(data)
  return(data);
}
writedata <- function(df){
  write.csv(df, file = 'result0328.csv')
}


data <-getcsv()
data$avg <- round(rowMeans(data[,c(4:6)]),2)

writedata(data)

newdata <- read.csv('result0328.csv',header = F, stringsAsFactors = F, encoding = 'UTF-8');
newdata

