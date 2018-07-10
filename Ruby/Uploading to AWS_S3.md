### AWS S3에 업로드하기

1. AWS 가입 및 IAM 설정 하기

2. S3 bucket 만들기

3. **[`figaro`](https://github.com/laserlemon/figaro) 설정!!!!**

   ```ruby
   gem 'figaro'
   ```

   ```
   $ bundle install
   $ bundle exec figaro install
   ```

   ```yaml
   # app/config/application.yml
   AWS_ACCESS_KEY_ID: asdf
   AWS_SECRET_ACCESS_KEY: 1q2w3e
   AWS_REGION: ap-northeast-2
   S3_BUCKET_NAME: tak-fake-insta
   ```

4. Gemfile[ `fog-aws`](https://github.com/fog/fog-aws)

   ```ruby
   gem 'fog-aws'
   ```

5. `config/initializers/fog-aws.rb` 생성

   ```ruby
   require 'fog'
   CarrierWave.configure do |config|
     config.fog_provider = 'fog/aws'                     
     config.fog_credentials = {
       provider:              'AWS',                        
       aws_access_key_id:     ENV["AWS_ACCESS_KEY_ID"],                       
       aws_secret_access_key: ENV["AWS_SECRET_ACCESS_KEY"],                        
       region:                ENV["AWS_REGION"],               
     }
     config.fog_directory  = ENV["S3_BUCKET_NAME"]                                             
   end
   
   ```

6. `app/uploaders/img_uploader.rb`

   ```ruby
   ..
   # storage :file
   storage :fog
   ```


7. S3 bucket 확인
