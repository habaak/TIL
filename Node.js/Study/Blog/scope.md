#JavaScript : scope

JavaScript 프로그래밍에서 유효범위를 잘 알아야 하는 이유가 무엇일까요? 제 생각은 다음과 같습니다.

유효범위란 JavaScript에서뿐만 아니라 모든 프로그래밍 언어 코드의 가장 기본적인 개념의 하나로 반드시 알아야 합니다. 유효범위의 개념을 모르면 관련된 다른 개념 역시 혼란스러울 수 있습니다.

JavaScript의 유효범위에는 다른 언어의 유효범위와는 다릅니다. 다른 프로그래밍 언어에 익숙한 개발자들은 JavaScript만의 유효범위를 이해해야 합니다.

###1.유효범위 (Scope)
#####전역유효범위 vs 지역유효범위
전역유효범위 : 스크립트 전체에서 참조되는 것
지역유효범위 : 정의된 함수 안에서만 참조

####1.1 JavaScript 유효범위의 특징
1) 함수 단위의 유효범위란


```JavaScript
function scopeTest() {  
    var a = 0;
    if (true) {
        var b = 0;
        for (var c = 0; c < 5; c++) {
            console.log("c=" + c);
         }
         console.log("c=" + c);
    }
    console.log("b=" + b);
}
scopeTest();  
//실행결과
/*
c = 0  
c = 1  
c = 2  
c = 3  
c = 4  
c = 5  
b = 0  
*/
```
위의 코드는 JavaScript의 유효범위 단위가 블록 단위가 아닌 함수 단위로 정의된다는 것을 설명하기 위한 예제 코드입니다. 다른 프로그래밍 언어들은 유효범위의 단위가 블록 단위이기 때문에 위의 코드와 같은 if문, for문 등 구문들이 사용되었을 때 중괄호 밖의 범위에서는 그 안의 변수를 사용할 수 없습니다. 하지만 JavaScript의 유효범위는 함수 단위이기 때문에 예제코드의 변수 a,b,c모두 같은 유효범위를 갖습니다. 그 결과, 실행화면을 보면 알 수 있듯이 구문 밖에서 그 변수를 참조합니다.

2) 변수명의 중복 허용
```JavaScript
var scope = 10;  
function scopeExam(){  
    var scope = 20;
    console.log("scope = " +scope);
}
scopeExam();  
//실행결과
/*
scope =20  
*/
```
JavaScript는 다른 프로그래밍 언어와는 달리 변수명이 중복되어도 에러가 나지 않습니다. 단, 같은 변수명이 여러 개 있는 변수를 참조할 때 가장 가까운 범위의 변수를 참조합니다. 위의 코드 실행화면을 보면 함수 내에서 scope를 호출했을 때 전역 변수 scope를 참조하는 것이 아니라 같은 함수 내에 있는 지역변수 scope를 참조합니다.

3) var 키워드의 생략
```JavaScript
function scopeExam(){  
    scope = 20;
    console.log("scope = " +scope);
}

function scopeExam2(){  
    console.log("scope = " + scope);
}
scopeExam();  
scopeExam2();  
//실행결과
/*
scope=20  
scope=20  
*/
```

4) 렉시컬 특성
```JavaScript
function f1(){  
    var a= 10;
    f2();
}
function f2(){  
    return console.log("호출 실행");
}
f1();

//실행결과
/*

호출실행
*/
```
```JavaScript
function f1(){  
    var a= 10;
    f2();
}
function f2(){  
    return a;
}
f1();

//실행결과
/*
Uncaught Reference Error  
: a is not defined
*/
```
렉시컬 특성이란 함수 실행 시 유효범위를 함수 실행 환경이 아닌 함수 정의 환경으로 참조하는 특성입니다. 위의 상단 코드를 봤을 때 함수 f1에서 함수 f2를 호출하면 실행이 됩니다. 함수 f1,f2 모두 전역에서 생성된 함수여서 서로를 참조할 수 있죠. 하지만 하단 코드처럼 함수 f1안에서 함수 f2를 호출했다고 해서 f2가 f1안에 들어온 것처럼 f1의 내부 변수 a를 참조할 수 없습니다. 렉시컬 특성으로 인해서 함수 f2가 실행될 때가 아닌 정의 될 때의 환경을 보기 때문에 참조하는 a라는 변수를 찾을 수 없습니다

####1.2 호이스팅 (Hoisting)
> Hoisting - 끌어올리기, 들어 올려 나르기
>> JavaScript에서는 변수 선언문을 끌어올린다는 뜻

```JavaScript
function hoistingExam(){  
    console.log("value="+value);
    var value =10;
    console.log("value="+value);
}
hoistingExam();

//실행결과
/*
value= undefined  
value= 10  
*/
```
```JavaScript
function hoistingExam(){  
    var value;
    console.log("value="+value);
    value =10;
    console.log("value="+value);
}
hoistingExam();  
//실행결과
/*
value= undefined  
value= 10  
*/
```
상단 코드를 보시게 되면 함수 hoistingExam안에서 변수 value의 호출이 두 번 일어납니다. 한 번은 변수 선언문 전에 또 한 번은 변수 선언 후에 호출이 되는데, 다른 프로그래밍 언어의 경우에는 선언문 전에 호출됐을 때 에러가 납니다. 하지만 JavaScript의 경우 호이스팅이 됨으로써 오른쪽 코드와 같은 구동이 이루어집니다. 즉, 변수 선언문이 유효범위 안의 제일 상단부로 끌어올려 지게 되고, 선언문이 있던 자리에서 초기화가 이루어지는 결과를 갖는 것입니다. 그 실행결과 첫 번째 호출에서는 초기화가 되지 않은 undefined가, 두 번째 호출에서는 초기화된 값이 나옵니다.


```JavaScript
var value=30;  
function hoistingExam(){  
    console.log("value="+value);
    var value =10;
    console.log("value="+value);
}
hoistingExam();  
//실행결과
/*
value= undefined  
value= 10  
*/
```
그렇다면 위와 같은 코드에서는 어떤 결과가 나올까요? 다른 프로그래밍 언어에 익숙한 개발자 분들은 변수 value의 첫 호출에서 전역변수가 참조된다고 생각하실 수 있습니다. 하지만 JavaScript의 호이스팅으로 인해서 선언 부가 함수 hoistingExam의 최 상단에서 끌어올려 짐으로써 전역변수가 아닌 지역변수를 참조합니다.

함수의 호이스팅을 이해할 때는 한 가지만 기억하시면 될 것 같습니다. 바로, 여러 가지의 함수 정의 방법 중 ‘함수 선언문 방식만 호이스팅이 가능하다.’라는 점입니다.

```JavaScript
// 함수 선언문
hoistingExam();  
function hoistingExam(){  
    var hoisting_val =10;
    console.log("hoisting_val ="+hoisting_val);
}
//실행결과
/*
hoisting_val =10  
*/
```
```JavaScript
//함수 표현식
hoistingExam2();  
var hoistingExam2 = function(){  
    var hoisting_val =10;
    console.log("hoisting_val ="+hoisting_val);
}
//실행결과
/*
hoistingExam2 of object is not a function  
*/
```
```JavaScript
//Function 생성자
hoistingExam3();  
var hoistingExam3 = new Function("","return console.log('Ya-ho!!');");  
//실행결과
/*
hoistingExam3 of object is not a function  
*/
```
함수 표현 식과 Function생성자를 통한 함수 정의 방법은 변수에 함수를 초기화시키기 때문에(이를 함수변수라고도 합니다) 선언문이 호이스팅이 되어 상단으로 올려진다 하더라도 함수가 아닌 변수로써 인지되기 때문입니다. 위의 코드에서 함수실행 구문이 아닌 변수를 호출하게 되면 변수의 호이스팅과 같은 undefined란 결과가 나옵니다.

####1.3 실행 문맥(Execution context)
실행 문맥은 간단하게 말해서 실행 정보입니다. 실행에 필요한 여러 가지 정보들을 담고 있는데 정보란 대부분 함수를 뜻합니다. JavaScript는 일종의 콜 스택(Call Stack)을 갖고 있는데, 이 곳에 실행 문맥이 쌓입니다. 콜 스택의 제일 위에 위치하는 실행 문맥이 현재 실행되고 있는 실행 문맥이 되는 것이죠.
```JavaScript
console.log("전역 컨텍스트 입니다");  
function Func1(){  
    console.log("첫 번째 함수입니다.");
};
function Func2(){  
    Func1();
    console.log("두 번째 함수입니다.");
};
Func2();  
//실행결과
/*
전역 컨텍스트 입니다
첫 번째 함수 입니다.
두 번째 함수 입니다
*/
```
####1.4 실행 문맥 생성
* 활성화 객체 : 실행에 필요한 여러 가지 정보들을 담을 객체입니다. 여러 가지 정보란 arguments객체와 변수등을 말합니다.
* 유효범위 정보 : 현재 실행 문맥의 유효 범위를 나타냅니다.
* this 객체 : 현재 실행 문맥을 포함하는 객체 입니다.
![](http://www.nextree.co.kr/content/images/2016/09/jsseo-140320-Scope-04-1024x378.png)
위의 코드를 실행하게 되면 함수abcFunction의 실행 문구에서 위와 같은 실행 문맥이 생깁니다. 실행문맥 생성 순서는 다음과 같습니다.

1.활성화 객체 생성
2.arguments객체 생성
3.유효범위 정보 생성
4.변수 생성
5.this객체 바인딩
6.실행

arguments객체는 함수가 실행될 때 들어오는 매개변수들을 모아놓은 유사 배열 객체입니다. 위의 그림에서 Scope Chain이 유효범위 정보를 담는 일종의 리스트이며 0번지는 전역 변수 객체를 참조합니다. Scope Chain에 대해서는 뒤에 다시 한 번 설명하겠습니다. 변수들은 위의 코드의 지역변수와 매개변수 a,b,c 입니다. 매개변수 a와 b는 실행 문맥 생성단계에서 초기화 값이 들어가지만, c의 경우 생성 후 실행 단계에서 초기화가 되기 때문에 undefined란 값을 가지고 생성됩니다.

###2. 유효범위 체인(Scope Chain)
유효범위 체인을 간단하게 설명하면 함수가 중첩함수일 때 상위함수의 유효범위까지 흡수하는 것을 말합니다. 즉, 하위함수가 실행되는 동안 참조하는 상위 함수의 변수 또는 함수의 메모리를 참조하는 것입니다. 앞서 실행 문맥 생성에 대해 설명했듯이 함수가 실행될 때 유효범위를 생성하고 해당 함수를 호출한 부모 함수가 가진 활성화 객체가 리스트에 추가됩니다.

![유효범위 체인 관계 형성](http://www.nextree.co.kr/content/images/2016/09/jsseo-140320-Scope-09-1024x480.png)

쉽게 말해서 위와 같은 코드를 실행할 때 전역 변수 객체, 상 하위 객체 간에 부모/자식 관계가 형성된다고 생각하시면 쉽게 이해 할 수 있습니다. 위의 코드를 실행 문맥 개념으로 좀 더 자세히 보면 다음과 같은 구조를 가집니다.

![유효범위 체인](http://www.nextree.co.kr/content/images/2016/09/jsseo-140320-Scope-08-1.png)
(앞으로 활성화 객체는 변수 객체와 같기 때문에 변수 객체라고 부르겠습니다) 함수 outerFunction이 실행 되면 outerFunction의 실행 문맥이 생성이 되고 그 과정은 앞선 실행 문맥 생성과정과 동일합니다. outerFunction이 실행이 되면서 내부 함수 innerFunction이 실행되면 innerFunction실행 문맥이 생성이 되는데 유효범위 정보가 생성이 되면서 outerFuction과는 조금 차이가있는 유효범위 체인 리스트가 생깁니다. innerFunction 실행문맥의 유효범위 체인 리스트는 1번지에 상위 함수인 outerFunction의 변수 객체를 참조합니다. 만약 innerFunction내부에 새로운 내부 함수가 생기게 되면 그 내부함수의 유효범위 체인의 1번지는 outerFunction의 변수 객체를, 2번지는 innerFunction의 변수 객체를 참조합니다.
