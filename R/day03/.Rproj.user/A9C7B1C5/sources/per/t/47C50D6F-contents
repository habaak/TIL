install.packages('sqldf')
library(sqldf)
library(ggplot2)
sqldf('select * from mpg')
mympg <- sqldf('select *, (cty+hwy)/2 as total from mpg')
mympg
mpg
true <- sqldf(' select total from mympg where total>=20')
false <- sqldf(' select total from mympg where total<20')

mympg2 <- sqldf(' select *, case when total >= 30 then "A" when total<30 and total >=20 then "B" else "c" end as grade from mympg')
mympg2
qplot(mympg2$grade)
