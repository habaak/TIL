job_male <- welfare %>%
  filter(!is.na(job)& sex == "male") %>%
  group_by(job) %>%
  summarise(n = n()) %>%
  arrange(desc(n)) %>%
  head(10)
job_male

job_female <- welfare %>%
  filter(!is.na(job)& sex == "female") %>%
  group_by(job) %>%
  summarise(n = n()) %>%
  arrange(desc(n)) %>%
  head(10)
job_female

ggplot(data = job_male, aes(x = reorder(job,n), y = n))+ geom_col() + coord_flip()

ggplot(data = job_female, aes(x = reorder(job,n), y = n))+ geom_col() + coord_flip()
