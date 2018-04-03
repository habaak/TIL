#Node.JS 프로젝트 시작하기
###1. 프로젝트 폴더 트리 생성
express-generator를 사용해서 기본 파일 생성하기 위해서 아래의 명령어를 이용해서 express-generator를 설치하자.
```
> npm install -g express-generator
```
설치 후 '-h'옵션을 이용해 명령어를 살펴보면,
```
>express -h

  Usage: express [options] [dir]


  Options:

        --version        output the version number
    -e, --ejs            add ejs engine support
        --pug            add pug engine support
        --hbs            add handlebars engine support
    -H, --hogan          add hogan.js engine support
    -v, --view <engine>  add view <engine> support (dust|ejs|hbs|hjs|jade|pug|twig|vash) (defaults to jade)
        --no-view        use static html instead of view engine
    -c, --css <engine>   add stylesheet <engine> support (less|stylus|compass|sass) (defaults to plain css)
        --git            add .gitignore
    -f, --force          force on non-empty directory
    -h, --help           output usage information
```
-h : 옵션 출력
-V : express의 버전 출력
-e : ejs 렌더링 엔진을 사용. default는 jade
-H : hogan.js 엔진 지원 추가
-C : 스타일 시트 엔진 ( less | styleus | compass | sass ) default는 css
-f : 비어있지 않은 디렉토리를 강제적으로 사용
```
>express -c sass _________
```

```
>express -c sass wehavebeenthere

  warning: the default view engine will not be jade in future releases
  warning: use `--view=jade' or `--help' for additional options


   create : wehavebeenthere\
   create : wehavebeenthere\public\
   create : wehavebeenthere\public\javascripts\
   create : wehavebeenthere\public\images\
   create : wehavebeenthere\public\stylesheets\
   create : wehavebeenthere\public\stylesheets\style.sass
   create : wehavebeenthere\routes\
   create : wehavebeenthere\routes\index.js
   create : wehavebeenthere\routes\users.js
   create : wehavebeenthere\views\
   create : wehavebeenthere\views\error.jade
   create : wehavebeenthere\views\index.jade
   create : wehavebeenthere\views\layout.jade
   create : wehavebeenthere\app.js
   create : wehavebeenthere\package.json
   create : wehavebeenthere\bin\
   create : wehavebeenthere\bin\www

   change directory:
     > cd wehavebeenthere

   install dependencies:
     > npm install

   run the app:
     > SET DEBUG=wehavebeenthere:* & npm start
```
***
참고 링크
[npm - express-generator](https://www.npmjs.com/package/express-generator)

[html5around.com](http://html5around.com/wordpress/tutorials/node-js-%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD-%EA%B5%AC%EC%B6%95%ED%95%98%EA%B8%B0/)
