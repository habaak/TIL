install.packages('readxl')
library(readxl)

exdata <- function(){
  library(readxl)
  data <- read_excel('excel_exam_3.xlsx',sheet = 2);
  return (data);
}

writedata <- function(df){
  write.csv(df, file = 'result0328.csv')
}

data <- exdata();
data$avg <- round(rowMeans(data[,c(4:6)]),2)
write.csv(data)

mean(data$math)
colnames(data) <- c('a','b','c','d','e')
data

