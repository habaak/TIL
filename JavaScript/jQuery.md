### jQuery
#### 1. 초기설정
```javascript
var scriptJ = document.createElement("script");
scriptJ //<script></script>
scriptJ.src = "https://code.jquery.com/jquery-3.3.1.min.js"
document.head.appendChild(scriptJ)
```
#### 2. jQuery DOM 조작법
`#('선택자')`


  1) `#('#id')`- id가 id인 tag를 찾아서 가져온다

  2) `#('.class')` - class가 class인 tag를 찾아서 가져온다

  3)  `#('ul > li > a > span')`- 부모와 자식의 관계를 ' > '를 통해 접근해 가져올 수 있다.

  4) `#('[href]')`- href라는 attribute를 가진 모든 tag를 가져온다.
    ```javascript
    $('[href="http://naver.com"]')
    ```
  5) `$('text')` - 텍스트에 해당하는 html tag를 가져와 줌
#### 3. eq
1. $().eq(index) => jQuery로 선택된 elements들 중에 index에 해당하는 element만 반환함.
```javascript
// 4번째 tag에 접근
$('span.an_icon').eq(3).css('display','none')
$('span.an_icon:eq(3)').css('display','')
// text에 접근
```
#### 4. text, html, val
  1. $().text - 자식 tag들 안에 있는 text만을 가져와줌
  2. $().html - 자식 tag들을 text 형태로 가져온다.
  3. $().val - input tag안의 내용을 가져온다.
```javascript
$('span.ah_k').eq(3).text()
$('span.ah_k').eq(3).text("habaak") //값을 변경함 
// class가 여러개인 경우 . 으로 연결
// html 태그를 text로 반환
$('a.an_a.mn_cafe').html() //"<span class="an_icon"></span><span class="an_txt">카페</span>"

$('input#query').val() //값을 가져옴
$('input#query').val("야야야") // 값을 변경함
```
