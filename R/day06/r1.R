library(foreign)
library(dplyr)
library(ggplot2)
library(readxl)
raw_welfare <- read.spss(file = 'Koweps_hpc10_2015_beta1.sav' ,to.data.frame = T)
welfare <- raw_welfare
str(welfare)

welfare<-rename(welfare,sex=h10_g3,
                birth=h10_g4,
                marriage=h10_g10,
                religion=h10_g11,
                income=p1002_8aq1,
                code_job=h10_eco9,
                code_region=h10_reg7)


gender <- table(welfare$sex)
welfare$sex <- ifelse(welfare$sex==1,'male','female')
qplot(welfare$sex)

