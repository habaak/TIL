# 
exam %>% group_by(class) %>% summarise(mean_math=mean(math))

exam %>%
  group_by(class) %>%
  summarise(
    mean_math=mean(math),
    sum_english=sum(english),
    mean_science=mean(science)
  )

mpg %>%
  group_by(manufacturer,drv) %>%
  summarise(
    mcty=mean(cty)
    
  ) %>%
  arrange(desc(mcty))

mpg %>%
  group_by(class) %>%
  summarise(
    avg_city=mean(cty),
    avg_highway=mean(hwy)
  ) %>%

mpg %>%
  group_by(class) %>%
  summarise(
    avg_city=mean(cty),
    avg_highway=mean(hwy)
  ) %>%
  arrange(desc(avg_city))

mpg %>%
  group_by(manufacturer) %>%
  summarise(
    avg_highway=mean(hwy)
  ) %>%
  arrange(desc(avg_highway)) %>%
  head(3)

mpg %>%
  group_by(manufacturer) %>%
  summarise(
    sum_compact=sum(class %in% 'compact')
  ) %>%
  arrange(desc(sum_compact))
