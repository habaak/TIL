getcsv <- function(){
  data <- read.csv('crime.csv');
  class(data)
  return(data);
}
writedata <- function(df){
  write.csv(df, file = 'crimeData.csv')
}

data <-getcsv()
class(data);
data.frame(data);
data
main_cat <- c('강력범죄', '강력범죄', '강력범죄', '강력범죄', '강력범죄', '강력범죄', '강력범죄', '강력범죄', '절도범죄', '폭력범죄', '폭력범죄');
sub_cat <- c('살인기수','살인미수 등','강도','강간','유사강간','강제추행','기타강간','방화','절도범죄','상해','폭행');
city<- data[1,c(4:length(data[1,]))]
city
data
