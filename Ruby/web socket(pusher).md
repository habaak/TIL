`rails _4.2.10_ new chatservice`

`figaro install`

`rails g devise:install`

`rails g devise users`

`rails g scaffold chat_room title master_id max_count:integer admission_count:integer`

`rails g model chat user:references chat_room:references message:text`

`rails g model admission user:references chat_room:references`




## Rails Simple WebSocket for Chatting (0720)

### https://pusher.com/



```ruby
#gemfile
...
gem "figaro"
gem "devise"
gem "pusher"
...
```

```bash
$ bundle install
$ figaro install
$ rails g devise:install
$ rails g devise users
```



#### 채팅: model

- 채팅방: 채팅내용(1:N)

  ```bash
  $ rails g scaffold chat_room title master_id max_count:integer admission_count:integer
  $ rails g model chat user:references chat_room:references message:text
  ```

- 채팅방: 사람(M:N)

  ```bash
  $ rails g model admission user:references chat_room:references
  ```



#### admission_count 디폴트 값 추가

```ruby
# db/mirgrate/xxxxx.create_chat_rooms.rb
class CreateChatRooms < ActiveRecord::Migration
  def change
    create_table :chat_rooms do |t|
      t.string :title
      t.string :master_id
      t.integer :max_count
      t.integer :admission_count, default: 0
      t.timestamps null: false
    end
  end
end
```

> $ rake db:migrate



#### 관계설정

```ruby
# model/chat_room.rb
class ChatRoom < ActiveRecord::Base

chat 1:N

  has_many :chats

user M:N, admission

  has_many :users, through: :admission

  has_many :admission

end

```



```ruby
# model/user.rb
class User < ActiveRecord::Base
    #Include default devise modules. Others available are:
	# :confirmable, :lockable, :timeoutable and :omniauthable
    devise :database_authenticatable, :registerable,
           :recoverable, :rememberable, :trackable, :validatable
    # 채팅이랑 1:N
 	has_many :chats
  	# 채팅방이랑 M:N
  	has_many :admissions
  	has_many :chat_rooms, through: :admissions


```

```ruby
# model/admission.rb
class Admission < ActiveRecord::Base
  belongs_to :user
  belongs_to :chat_room, counter_cache: true
end
```



#### Pusher API 설정

```ruby
# config/application.yml
pusher_app_id: ''
pusher_key: ''
pusher_secret: ''
pusher_cluster: ''
```



```ruby
# config/initializers/pusher.rb
require 'pusher'
Pusher.app_id = ENV['pusher_app_id']
Pusher.key = ENV['pusher_key']
Pusher.secret = ENV['pusher_secret']
Pusher.cluster = ENV['pusher_cluster']
Pusher.logger = Rails.logger
Pusher.encrypted = true
​```
```
