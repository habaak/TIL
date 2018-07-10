## [Carrierwave](https://github.com/carrierwaveuploader/carrierwave)

>파일 혹은 이미지 업로드의 경우 클라이언트에게  `<input type="file">` 을 통해 받을 수 있다. 서버에서 파일을 저장 및 관리하기 위해서는 `carrierwave` 가 필요하다.

> 내 서버에 직접 파일들을 저장할 수 있지만, `fog-aws`를 통해서 AWS의 S3에 저장을 할 수도 있다.



1. Gem설치 

   ```ruby
   gem 'carrierwave'
   ```

2. uploader 설정 파일 생성

   ```command
   $ bundle install
   $ rails g uploader Img
   ```

   

3. `app/uploaders/img_uploader.rb`

   ```ruby
   class ImgUploader < CarrierWave::Uploader::Base
     # Include RMagick or MiniMagick support:
     # include CarrierWave::RMagick
     include CarrierWave::MiniMagick
   
     # Choose what kind of storage to use for this uploader:
     # storage :file
     storage :fog
   
     # Override the directory where uploaded files will be stored.
     # This is a sensible default for uploaders that are meant to be mounted:
     def store_dir
       "uploads/#{model.class.to_s.underscore}/#{mounted_as}/#{model.id}"
     end
   
     # Provide a default URL as a default if there hasn't been a file uploaded:
     # def default_url(*args)
     #   # For Rails 3.1+ asset pipeline compatibility:
     #   # ActionController::Base.helpers.asset_path("fallback/" + [version_name, "default.png"].compact.join('_'))
     #
     #   "/images/fallback/" + [version_name, "default.png"].compact.join('_')
     # end
   
     # Process files as they are uploaded:
     # process scale: [200, 300]
     #
     # def scale(width, height)
     #   # do something
     # end
   
     # Create different versions of your uploaded files:
     version :thumb_fit do
       process resize_to_fit: [250, 250]
     end
     # 1000*800 -> 250*200
   
     version :thumb_fill do
       process resize_to_fill: [250, 250]
     end
     # 1000*800 -> 250*250
   
     # Add a white list of extensions which are allowed to be uploaded.
     # For images you might use something like this:
     def extension_whitelist
       %w(jpg jpeg gif png)
     end
   
     # Override the filename of the uploaded files:
     # Avoid using model.id or version_name here, see uploader/store.rb for details.
     # def filename
     #   "something.jpg" if original_filename
     # end
   end
   
   ```

4. 이미지 저장을 할 column 추가

   ```
   $ rails g migration AddImgToPosts img:string 
   $ rake db:migrate
   ```

5. `model` 설정

   ```ruby
   # app/models/post.rb
   mount_uploader :img, ImgUploader
   ```

6. `input` 설정

   ```erb
   # app/views/posts/_form.html.erb
   <%= simple_form_for @post, html: {multipart: true} do |f| %>
   ..
   <%= f.input :img %>
   ..
   ```

7. `controller` 설정

   ```ruby
   # app/controllers/post_controller.rb
   ..
   def post_params
     params.require(:post).permit(:title, :contents, :img) 
   end
   ```

8. `public/uploads/.....` 파일이 업로드 됨!

### [이미지 버전 만들기](https://github.com/minimagick/minimagick)

> 실제로 이미지 활용에 따라 각기 다른 버전의 이미지를 잘라서 저장할 수 있다. 이때 사용되는 것은 `mini_magick` 이라는 Gem이며, 이를 활용하기 위해서는 반드시 `image magick`을 설치해야함.

0. OS별 설치 방법

   ```
   # ubuntu
   $ sudo apt-get update
   $ sudo apt-get install -y imagemagick
   
   # MacOS
   $ brew install imagemagick
   ```

1. Gemfile

   ```
   gem 'mini_magick'
   ```

2. `app/uploaders/img_uploader.rb`

   ```ruby
   ..
   include CarrierWave::MiniMagick
   ..
     version :thumb_fit do
       process resize_to_fit: [250, 250]
     end
     # 1000*800 -> 250*200
   
     version :thumb_fill do
       process resize_to_fill: [250, 250]
     end
     # 1000*800 -> 250*250
   ```

3. 활용

   ```erb
   <%= image_tag @post.img %>
   <%= image_tag @post.img.thumb_fill.url %>
   <%= image_tag @post.img.thumb_fit.url %>
