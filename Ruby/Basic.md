# Ruby

### 0. 개요
1. 루비는 순수 객체 지향 언어이다.
2. 루비는 모든것이 객체
3. Ruby on Rails



### 1. puts vs print vs p
```Ruby
3.times {puts "Park Junha"}


# Park Junha
# Park Junha
# Park Junha
```

```Ruby
3.times {print "Park Junha"}                      
# Park JunhaPark JunhaPark Junha => 3
```

```Ruby
array = [1,2,3]
# => [1, 2, 3]
puts array
1
2
3
p array
[1, 2, 3]
 => [1, 2, 3]

str = "Ruby~~"
 => "Ruby~~"
puts str
Ruby~~
 => nil
p str
"Ruby~~"
 => "Ruby~~"
 # 쌍따음표까지 보여준다
```
### 2. Naming Conventions
- 변수
  - 소문자, 여러 단어의 경우 snake_case
- 상수
  - CONSTANT
- 클래스 (Class Camel Case)
  - CamelCase

### 3. pry
- 설치
  - `gem install pry`
- 실행
  - `pry`

### 4. inline statement


```Ruby
# if 문
a = 0
=> 0
b = 1
=> 1
puts "a=0" if a==0
a=0
=> nil
#왼쪽에 있는 조건이 맞으면 실행을 한다.

# while 문
res = c+= 2 while c < 50
puts res
50
#왼쪽에 있는 조건까지 실행한다.

puts "hi" if 0                                                                     
hi
# 0은 True

case name
  when "habaak" then puts "hi habaak"  
  when "kwak" then puts "hi kwak"  
  else puts "hi"  
end  
```

### 5. Method
- 대부분의 언어
  - 클래스 안 : funcion
  - 클래스 밖 : method
```Ruby
# Ruby의 method 선언 방식
def simple
 puts "simple!!"
end  
=> :simple
# Ruby에서 method에 괄호를 생략할 수도 있다.
# 명확성을 위해 사용하기도 한다.
# return을 생략할 수 있고
def simple()
  puts "simple!!"
end  
=> :simple
```
```Ruby
#Ruby에서 return이 없을때 마지막 연산 값을 return한다.
def add(a, b)
  a + b
end
add(3,2)
=> 5

#return을 선택적으로 사용할 수 있다.
def divide(a,b)
  return "I don't think so" if b == 0
  a/b
end  
=> :divide
divide(4,0)
=> "I don't think so"
divide(4,2)
=> 2
```

```Ruby
def factorial(n)
   n == 0 ?1 : n*factorial(n-1)
end  
=> :factorial
factorial(3)
=> 6
```
