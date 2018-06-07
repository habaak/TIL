#Ruby
###2시간 동안 맛보는 챗봇

###문법
Ruby는 모든 것이 객체

####1. 저장
######DataType
 1. numeric
 2. string
 3. boolean

변수
 ```Ruby
 dust = 58
 dust = 60
 puts dust
```
베열
 ```Ruby
 dust = ["58","79"]
 puts dust
```

해쉬(key value)
```Ruby
dust = {"영등포구"=>58, "강남구"=> 40}
puts dust["영등포구"]
```

####2.조건
end로 마무리
괄호가 없어도 된다.
```Ruby
if
```

####3. 반복

```Ruby
3.time.do

end
```
```Ruby
array = [1,2,3,4]
for i in  array
  puts i
end

for i in array
puts i
end

[1,2,3].each do |j|
  puts j
end
```
---
###예제 - 멋쟁이 사자처럼 쳇봇

#####미세먼지 - api 사용과 조건문, 타입 변경
```Ruby
require 'httparty'
url = URI.encode("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=강남구&dataTerm=MONTH&numOfRows=100&ServiceKey=")+key
response = HTTParty.get(url)
dust = response['response']['body']['items']['item'][0]['pm10Value']
puts dust
puts dust.class
puts dust.to_i.class
#puts dust.methods

if(dust < "30")
  puts "좋음"
  elsif(dust < "80")
  puts "보통"
  elsif(dust < "150")
  puts "나쁨"
  else
  puts "매우나쁨"
end
```
#####로또 - method 사용 하기
```Ruby
# 1-45 숫자 저장
# sample을 통해 6개 숫자를 무작위로 뽑는다
# 새로운 박스를 출력한다
lotto = (1...45)
.to_a.sample(6)
.sort.to_s
puts lotto
```
#####코스피 - Nokogiri로 HTML 정보 추출
```Ruby
require 'httparty'
require 'nokogiri'

# 원하는 정보가 있는 주소로 접근
url = 'http://finance.naver.com/sise/'
# 요청을 보내고 응답을 저장
res = HTTParty.get(url)
data = Nokogiri::HTML(res.body)
puts data
# 조작하기 편하게 바꾸기
# 바꾼 정보중에서 원하는 정보만 뽑아서
# 출력
```

---
#C9 사용법

irb : c9 콘솔창에서 ruby 명령어 실행
gem : 
