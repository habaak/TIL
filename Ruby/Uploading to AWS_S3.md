### AWS S3에 업로드 하기

1. AWS 가입 및 IAM 설정하기
2. S3 bucket 만드기
3. **`figaro` 설정**
```
gem 'figaro'
```

```
```

```ruby
#app/config/application.yml
AWS_ACCESS_KEY_ID: 00000000000000000
AWS_SECRET_ACCESS_KEY: 00000000000000000
AWS_REGION: ap-northeast-2
S3_BUCKET_NAME: name
```

4. Gemfile
```
gem 'fog-aws'
```

5. `config/initializers/fog-aws.rb` 생성
```ruby
require 'fog'
CarrierWave.configure do |config|
  config.fog_provider = 'fog/aws'                        # required
  config.fog_credentials = {
    provider:              'AWS',                        # required
    aws_access_key_id:     ENV["AWS_ACCESS_KEY_ID"],                        # required unless using use_iam_profile
    aws_secret_access_key: ENV["AWS_SECRET_ACCESS_KEY"],                        # required unless using use_iam_profile
    # use_iam_profile:       true,                         # optional, defaults to false
    region:                ENV["AWS_REGION"],                  # optional, defaults to 'us-east-1'
    # host:                  's3.example.com',             # optional, defaults to nil
    # endpoint:              'https://s3.example.com:8080' # optional, defaults to nil
  }
  config.fog_directory  = ENV["S3_BUCKET_NAME"]                                      # required
  config.fog_public     = false                                                 # optional, defaults to true
  config.fog_attributes = { cache_control: "public, max-age=#{365.days.to_i}" } # optional, defaults to {}
end
```

6. `app/uploaders/img_uploader.rb`
```ruby
..
#storage :file
storage :fog
..
```

7. S3 bucket 확인
