install.packages('ggplot2')
library(ggplot2)
total <- midwest$poptotal
asian <- midwest$popasian
rateasian <- asian/total
hist(rateasian)
avgasian<-ifelse(mean(rateasian)<rateasian,"large","small")
table(avgasian)
qplot(avgasian)
