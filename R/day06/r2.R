class(welfare$income)
summary(welfare$income)
qplot(welfare$income)
qplot(welfare$income) + xlim(0,1000)

summary(welfare$income)
welfare$income <- ifelse(welfare$income %in% c(0,9999),NA,welfare$income)
table(is.na(welfare$income))
sex_income <- welfare %>%
  filter(!is.na(income)) %>%
  group_by(sex) %>%
  summarise(
    mean_income = mean(income)
  )
sex_income
ggplot(data = sex_income, aes(x=sex,y=mean_income))+geom_col()
class(welfare$birth)
summary(welfare$birth)
qplot(welfare$birth)

summary(welfare$birth)
table(is.na(welfare$birth))
welfare$birth <- ifelse(welfare$birth == 9999,NA,welfare$birth)
table(is.na(welfare$birth))

welfare$age <-2015-welfare$birth +1
summary(welfare$age)
qplot(welfare$age)
age_income <-welfare %>%
  filter(!is.na(income))%>%
  group_by(age)%>%
  summarise(mean_income = mean(income))
head(age_income)
ggplot(data = age_income,aes(x=age, y=mean_income))+geom_line()
