# Sinatra
### 0.Install
- `$ mkdir sinatra-test`
- `$ cd sinatra-test`
- `$ touch app.rb`
- `$ gem install sinatra`

### 1. 시작 페이지 만들기
app.js
```Ruby
#app.rb
require 'sinatra'

get '/' do
  send_file 'index.html' #index.html 파일을 보내줘
end
```
- 서버 실행
`$ ruby app.rb -o $IP`
외부 접속을 허용하기 위해서 IP를 바꿔주었습니다.

- Sinatra reloader - 자동 서버 재시작
`$ gem install sinatra-contrib`

```Ruby
require 'sinatra'
require 'sinatra/reloader'
```

app.rb
```rb
require 'sinatra'
require 'sinatra/reloader'


get '/' do
  'Hello world!'
end

get '/htmlfile' do
    send_file 'views/html.html'
end

get '/htmltag' do
    '<h1>html태그를 보낼 수 있습니다.</h1>
    <ul>
        <li>1</li>
        <li>22</li>
        <li>333</li>
        <li>4444</li>
    </ul>
    '
end

get '/welcome/:name' do
    "#{params[:name]}님 안녕하세요"
end

get '/cube/:res' do

    "<h1>""#{params[:res].to_i**3}""</h1>"
end

get '/erbfile' do
    @name = "joonhabaak"
    #erb 렌더링
    erb :erbfile
end

get '/lunch-array' do
    # 1. 점심메뉴들이 담긴 lunch 배열 만들기
    lunch = ["백종원", "국수", "버거킹", "20층"]
    @menu = lunch.sample.to_s
    erb :lunchfile
end

get '/lunch-hash' do
    lunch = ["백종원", "국수", "버거킹", "20층"]

    lunch_img = {
        "백종원"=>"http://pds.joins.com/news/component/htmlphoto_mmdata/201601/27/htm_201601279134262048.jpg",
        "국수"=>"http://t1.daumcdn.net/thumb/C230x172/?fname=http%3A%2F%2Fm1.daumcdn.net%2Fcfile62%2Fattach%2F21736C4F557E01781CB907",
        "버거킹"=>"http://www.burgerking.co.kr/Content/menu/image/main/burger_bulgogiwhopper.jpg",
        "20층"=>"http://ch.kimhyunjung.kr/web/product/extra/big/201803/493_shop1_549456.jpg"
    }

    @menu = lunch.sample
    @img = lunch_img[@menu]
    erb :lunchhash
end

```
### 2. 시작 페이지 만들기 (Routing 설정 및 view 설정)
##### 폴더 구조

* app.rb
* views/
  * .erb
  * layout.erb

##### layout.erb

```erb
<html>
    <head>
    </head>
    <body>
        <%= yield %>
    </body>
</html>
```

```ruby
def hello
    puts "hello"
    yield
    puts "bye"
end
# {} : block / 코드 덩어리
hello {puts "takhee"}
# => hello takhee bye
```
##### erb에서 Ruby코드 활용하기
`<%= @변수명 %>`
lunchfile.erbfile
```html
<h1>오늘의 추천 메뉴는?</h1>

<h3><%= @menu %>입니다!!!</h3>
```

lunchfile.erbfile
```html
<h1>오늘의 추천 메뉴는?</h1>

<h3><%= @menu %>입니다!!!</h3>
<img src="<%= @img %>" height="600px" weight="400px">
```

##### params

1. variable routing
```Ruby
# app.rb
get 'hello':name do
  @name = params[:name]
  erb :name
end
```
2. `form` tag를 통해서 받는 법
```html
<form action="/posts/create">
  제목 : <input name="title"></input>
</form>
```
```Ruby
# app.rb
# params
# {title: "title"}
get '/posts/create' do
  @title = params[:title]
end


### 3. ORM

```
##### Object Relational Mapper - 객체 관계 매핑
객체지향언어(ruby)와 데이터베이스(sqlite)를 연결하는 것을 도와주는 도구

[Datamapper]('http://recipes.sinatrarb.com/p/models/data_mapper')
`$ gem install datamapper`
`$ gem install dm-sqlite-adapter`

```Ruby
# C9에서 json 라이브러리
gem 'json','~> 1.6'

require 'sinatra'
require 'sinatra/reloader'
require 'data_mapper' # metagem, requires common plugins too.

# need install dm-sqlite-adapter
# blog.db 세팅
DataMapper::setup(:default, "sqlite3://#{Dir.pwd}/blog.db")
# Post 객체(Object) 생성
class Post
  include DataMapper::Resource
  property :id, Serial
  property :title, String
  property :body, Text
  property :created_at, DateTime
end

# Perform basic sanity checks and initialize all relationships
# Call this when you've defined all your models
DataMapper.finalize

# automatically create the post table
Post.auto_upgrade!

```

### 4. 데이터 조작
- 기본
`Post.all`
> Create
  ```Ruby
  # 1.
  Post.create(title:"test", body:"body")
  # 2.
  p = Post.new
  p.title = "test"
  p.body = "test"
  p.save #db에 저장
  ```
> Read
  ```Ruby
  Post.get(1) #get(id)
  ```
> Update
  ```Ruby
  # 1.
  Post.get(1).update(title:"test",body:"body")
  # 2.
  p = Post.get(1)
  p.title = "test"
  p.body = "test"
  p.save #db에 저장
  ```
> Destory
  ```Ruby
  Post.get(1).destory
  ```

### 5. CRUD 만들기

>  Create : action이 두개 필요

  ```ruby
  # 사용자에게 입력받는 창
  get '/posts/new' do
  end
  # 실제로 db에 저장하는 곳
  get 'posts/create' do
      Post.create(title: params[:title], body: params[:body])
  end
  ```

>  Read : variable routing

  ```ruby
  # app.rb
  get 'posts/:id' do
  	@post = Post.get(params[:id])
  end
  ```
