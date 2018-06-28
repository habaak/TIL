### jQuery
1. 초기설정
```javascript
var scriptJ = document.createElement("script");
scriptJ //<script></script>
scriptJ.src = "https://code.jquery.com/jquery-3.3.1.min.js"
document.head.appendChild(scriptJ)
```
2. 사용법
`#('#id')`- id
```javascript
```
`#('.class')` - class
```javascript
```
`#('ul > li > a > span')`- 경로
```javascript
```
`#('[href]')`- attribute
```javascript
$('[href="http://naver.com"]')
```
