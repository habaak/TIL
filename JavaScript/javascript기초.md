### 자료형

1. 숫자

2. 문자

3. null

4. undefined

5. boolean

6. 객체(Object) => {}

   1. 키와 값을 쌍으로 가진다

   2. var o = {key: value}

   3. 함수를 값으로 가질 수 있다.

   4. method, property (value에 함수가 들어있을 경우 method, 그 외의 값은 property)

   5. 배열(array) => []

      1. 유용한 methods: pop(맨 뒤에서 값을 뺀다), push(맨 뒤에 값을 넣는다), shift(맨 앞의 값 뺀다), unshift(맨앞에 값 넣음), sort, reverse, indexOf, forEach

      2. map, filter, reduce

         ~~~javascript
         //map 배열 내의 모든 값에 적용
         var arr = [1,2,3,4,5];
         arr.map(function(x){return x*x;});  //[1,4,9,16,25]

         //filter 배열의 모든 값에 함수를 적용시켜서 true인 값만 반환한다.
         var arr = [1,2,3,4,5];
         arr.filter(fucntion(x){return x>2});  //[3,4,5]

         //reduce
         ~~~

         - map, filter는 배열의 값을 바꾸지 않고 바뀐 값을 리턴한다.



### 함수

1. 함수 선언식

   ~~~javascript
   function sum(x,y){
       return x+y;
   }
   ~~~

2. 함수 표현식

   ~~~javascript
   var sum = function(x,y){
       return x+y;
   }
   ~~~

3. 함수 선언식과 함수 표현식의 차이점

   ~~~javascript
   //함수 선언식은 함수를 먼저 쓰고 나중에 선언해도 동작한다.
   //자바스크립트 hoisting => 함수나 변수는 자바스크립트가 실행될 때 맨 위로 가져온다
   sum(1,2);

   function sum(x,y){
       return x+y;
   }

   //실제 실행되는 순서
   function sum(x,y){
       return x+y;
   }

   sum(1,2);
   ~~~

   ~~~javascript
   //함수 표현식에서는 에러, 변수 선언만 hoisting 되기 때문에
   sum2(1,2);

   var sum2 = function(x,y){
       return x+y;
   }

   //실제 실행되는 순서
   var sum2;

   sum2(1,2);

   sum2 = function(x,y){
       return x+y;
   }
   ~~~

4. 함수의 다양한 용도 (인자, 리턴값)

   ~~~javascript
   //map함수 구현

   var arr = [1,2,3,4,5];
   var double = function(x){return x*2;};
   //var arr2 = arr.map(double);
   var arr2 = map(double,arr);
   function map(func,arr){
       new_arr = [];
       for (element of arr){
           new_arr.push(func(element));
       }
       return new_arr;
   }
   ~~~

   ~~~javascript
   //filter함수 구현

   var positive = function(x){return x>0;}
   //positive(1) //=> true
   //positive(2) //=> false

   var arr=[-1,3,-5,7,-9];
   var arr2 = filter(positive,arr);
   function filter(func,arr){
       var new_arr = [];
       for (element of arr){
           if (func(element)){
               new_arr.push(element);
           }   
       }
       return new_arr;
   }
   ~~~

   ~~~javascript
   //함수 리턴
   function func1(){
       return function func2(){
           console.log("im inner function");
       }
   };

   var test = func1();
   test();
   ~~~

5. 함수의 인자 사용법

   ~~~javascript
   function sum(a,b){
       console.log(a+b);
   }

   sum(1) //들어오지 않은 값은 undefined
   //console.log(1+undefined);
   // => NaN

   sum(1,2,3) // 더 들어오는 인자값도 arguments로 사용가능
   //더 들어오는 인자값도 arguments에 들어간다.
   //arguments = [1,2,3]


   //들어오는 모든 인자값을 더하기
   function sum(a,b){
       var total = 0;
       for(element of arguments){
           total += element;
       }
       console.log(total);
   }

   sum(1,2,3,4,5);  //=>15


   //들어오는 모든 인자값을 곱해서 리턴하는 함수
   function multiple(a,b){
       var total = 1;
       for(element of arguments){
           total *= element;
       }
       console.log(total);
   }

   multiple(1,1,3,1,5)  //=> 15
   ~~~



### 변수 scope

~~~javascript
//1.
var i = 0;
function changeI(){
    i = 10;
    console.log(i);
}

changeI();        //=>10
console.log(i);   //=>10


//2.
var i = 0;
function changeI(){
    var i = 10;
    console.log(i);
}

changeI();        //=>10
console.log(i);   //=>0
~~~

- 정적 유효범위

  ~~~javascript
  var i = 0;

  function a(){
      var i = 10;
      //함수 호출.
      b();   //함수 호출 시 변수를 참조하지 않고, 함수를 선언될 당시에 변수를 가져온다.  //=> 0
  }

  function b(){
      console.log(i);    //함수 선언될 떄 변수 참조, 이 때 i = 0;
  }


  a();   //=>0

  i= 10;
  a();  // => 10



  //함수 내 지역 변수를 선언하는 방법은 함수 내에서 함수를 선언해준다.
  var i =0;

  function a(){
      var i = 10;
      function b(){
          console.log(i);   //i = 10
      }
      b();   //=> 10
  }
  ~~~

- 클로저

  ~~~javascript
  var i = 0;
  function a(){
      var i = 10;
      return function b(){
          console.log(i);
      }
  }

  var closure = a();
  closure()  //=>10
  ~~~

### Hoisting(끌어올림)
  ```javascript
  // 실제 코드
  console.log(i)
  var i = 0;

  func();
  function func(){
    console.log("func!!")
  }
  ```
  ```javascript
  // 실행될 때 코드
  // 변수와 함수가 먼저 메모리에 올라간다.
  function func(){
    console.log("func!!");
  }
  var i;
  console.log(i); //콘솔에 찍히는 값은? undefined
  i = 0;
  func(); // 콘솔에 찍히는 값은? func!!
  ```
  ```javascript
  // 두번째 예시, 실제 코드
  var i = 0;
  function func2() {
    console.log(i);
    var i = 10;
  }

  func2() //콜솔에 찍히는 값은?  undefined
  ```
  ```javascript
  //실행되는 코드
  var i = 0;
  function func2() {
    var i; //끌어올려짐
    console.log(i);
    var i = 10;
  }
  func2()
  ```

  ```javascript
  // 실제코드
  var language = 'java'
  function checkScript(script){
    if(script) {
      var language = 'ruby';
      console.log(language);
    } else {
      console.log(language);
    }
  }
  checkScript(true); // 콜솔에 찍히는 값은? ruby
  checkScript(false); // 콜솔에 찍히는 값은? undefined

  ```
  ```javascript
  // 실행될 때 코드
  var language = 'java'
  function checkScript(script){
    var language // 끌어올려짐
    if(script) {
      var language = 'ruby';
      console.log(language);
    } else {  
      console.log(language);
    }
  }
  checkScript(true); // 콜솔에 찍히는 값은? ruby
  checkScript(false); // 콜솔에 찍히는 값은? undefined
  ```
  ```javascript
  // 3번째 예시, 해결책 1 - 전역변수를 참조
  var language = 'Java'

  function checkScript(script){
    if(script){
      language = "ruby";
      console.log(language);
    }else{
      console.log(language);
    }
  }
  checkScript(true)// 콜솔에 찍히는 값은? ruby
  checkScript(false); // 콜솔에 찍히는 값은? java
  ```
  ```javascript
  // 3번째 예시, 해결책 2 - 변수를 let으로 선언한다
  var language = 'Java'

  function checkScript(script){
    if(script){
      let language = "ruby";
      console.log(language);
    }else{
      console.log(language);
    }
  }
  checkScript(true)// 콜솔에 찍히는 값은? ruby
  checkScript(false); // 콜솔에 찍히는 값은? java
  ```

### 5. this
  ```javascript
  var globalThis = null;

  // 1. 함수에서 사용되는 this => 현제 코드가 실행되는 브라우저의 창
  function this1() {
    globalThis = this;
  }
  this1();
  globalThis // window => 현제 코드가 실행되는 브라우저의 창

  // 2. method에서 사용되는 this => this는 메소드를 사용하는 객체
  var o = {
    p1: 'property1',
    m1: this1
  };

  o.m1()
  globalThis
  // 2-1. 예시2
  var o2 = {
    prop1: 1,
    method: function() {
      console.log(this.prop1);
    }
  };

  o2.method(); // 콘솔에 찍히는 값은? 1=> this.prop1 == 02.prop1

  // 3. 생성자에서 사용되는 this
  function Person(name){ //생성자 함수, 클래스와 같은 역할을 한다.
    this.name = name; // this는 생성된 객체를 의미함
  }

  var p1 = new Person("Joseph"); // this == p1
  p1.name // => "Joseph"

  // 3-1.
  var globalThis = null;
  function this1() {
    globalThis = this;
  }
  var o1 = new this1();
  globalThis => o1
  ```

  ### this와 관련된 method (call, apply, bind)
  ```javascript
  // 1. call (this에 해당하는 대상, arg1, arg2)

  // 예제 1.
  function Person(name){
    this.name = name
  }
  var p1 = new Person('Joseph');
  var p2 = {};
  Person.call(p2, "Joseph"); //call (this, arguments)
  p2 // {name: "Joseph"}

  // 예제 2.
  function Person(name, age) {
    this.name = name;
    this.age = age;
  }
  var p3 = {};
  Person.call(p3, 'Joseph',33)
  p3 // {name: "Joseph", age: 33}

  // 예제 3.
  var globalThis = null;
  function testFunc() {
    globalThis = this;
  }

  var testVar = 20;
  testFunc.call(testVar);

  // 예제 4.
  var globalThis = null;
  function testFunc(a, b) {
    globalThis = this;
    console.log(a+b);
  }

  testFunc.call(20,1,2) //첫 번째 인자는 this 값
  globalThis => 20
  // 로그 값 3

  // 2. apply (this에 해당하는 대상, [arg1, arg2...])
  //call이랑 동일함, argument를 넣는 방식만 다름.
  Person.call(p3, "Joseph", 33) // ,로 구분해서 인자를 정해줌.
  Person.apply(p3, ["Joseph",33]) // 배열 안에 몽땅 넣어서 전해줌

  // 3. bind (this에 해당하는 대상) & 함수가 실행되진 않음. 지정만 해주고 끝남 (실행x)
  var globalThis = null;
  function testFunc(a,b) {
    globalThis = this;
    console.log(a+b);
  }

  var bindedFunc = testFunc.bind(20)
  ```

7. Closure (외부 함수의 변수들에 접근 가능한 내부 함수)
```javascript

// closure (외부 함수 안에 있는 내부 함수)
// 내부함수는 외부함수의 변수들에 접근 가능.
// 외부함수는 내부함수에 접근 불가.
var i
function outer() {
  var i = 10;
  var j = 20;
  var k = 30;
  function inner() {
    var innerVar =100;
    console.log(i);
    console.log(j);
    console.log(k);
  }
  // console.log(innerVar); // 접근불가
  return inner;
}
var closure = outer(); //변수 closure에는 함수 inner가 들어 있는
closer()
var closure =fucntion outer(i) {
  return function inner() {
    console.log(i);
  }
}
```
```javascript
var arr = [];
for(var i=0; i<10; i++) {
  arr[i] = function() {
    return i * 20;
  };
}

for(j in arr) {
  console.log(arr[j]());
}

var arr = [];
function a(){
  var i
    return function b() {
      for(var i=0; i<10; i++){
      console.log(i * 20);
    }
  }(i)
}

// version 1.
var arr=[];
for(var i=0; i<10; i++) {
  arr[i] = function ouster(i){
    function inner(){
      return i * 20;
    }
    return inner;
  }(i);
}

// version 2.
function outer(i) {
  return function inner() {
    return i*20;
  }
}
for(var i=0; i<10; i++) {
  arr[i] = outer(i);
}

// 출력 코드
for(j in arr) {
  console.log(arr[j]());
}
```

### 8. Prototype (상속, 클래스 변수를 정의하는 것과 같은 역할)

1. Prototype을 통해 상속을 구현할 수 있다.
 `child.Prototype = new Parnet();``
2. Prototype을 통해 메서드와 프로퍼티를 객체 간에 공유할 수 있다.
```javascript
Child.Prototype.name = "Joseph"
var c1 = new Child();
var c2 = new Child();
c1.name == c2.name //Joseph
```
```javascript
function Person() {
  this.purpose = "happiness";
}
function Adult() {
  this.age = "higher than 20"
}
function Child() {
  this.age = "lower than 20"
}

Adult.Prototype = new Person();
Child.Prototype = new Person();

var a1 = new Adult();
a1.purpose;

var c1 = new child();
c1.purpose;

var p1 = new Person();
var p2 = new Person();
Person.prototype.name = "Joseph"; // person 그 자체에 접근한다.  
Person.prototype.pritntName = function(){
  console.log(this.name);
}
```
