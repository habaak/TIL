### 1. Java의 특징
> Java는 네트워상에서 쓸 수 있도록 미국의 Sun micro systems가 개발한 **객체 지향 프로그래밍 언어**

a. JVM만 설치하면 컴퓨터의 운영체제에 상관없이 작동한다. => 운영체제에 독립적이다.
b. 기본 자료형을 제외한 모든 요소들이 객체로 표현 가능하다.
c. 객체 지향 개념의 특징인 캡슐화, 상속, 다형성이 잘 적용된언어
d. Garbage Collector 자동적인 메모리 관리
e. Multi-thread를 지원

> 운영체제에 상관없이 작동
객체지향언어 - 캡슐화, 상속, 다형성
Garbage Collector
Multi-thread

### 2. OOP
순차적으로 동작하는 프로그램과 다르게 데이터를 객체로 취급하여 프로그램에 반영한 것

a. 코드의 재사용성이 높다.
b. 코드의 변경이 용이
c. 직관적인 코드 분석
d. 개발 속도 향상
e. 상속을 통한 장점 극대화

### 3. Object vs Class
객체(object) : 실제로 존재하는 구체적인 대상으로 상태(Attribute)와 행위(Method)를 가진다.
클래스(class) : 객체를 추상화 시킨것
![object-class](http://thumbnail.egloos.net/600x0/http://pds15.egloos.com/pds/200906/01/53/c0004553_4a238b5fb60b1.jpg)

### 4. Overloading vs Overriding
Overloading(오버로딩)

- **같은 이름의 메소드를 여러개 정의**하는 것
- **매개변수의 타입이 다르거나 개수가 달라야 한다.**
* return type과 접근 제어자는 영향을 주지 않음.

Overriding(오버라이딩)

- 상속에서 나온 개념

- 상위 클래스(부모 클래스)의 메소드를 하위 클래스(자식 클래스)에서 재정의

### 5. Servlet, JSP

Servlet - Container가 이해할 수 있게 구성된 순수 자바 코드로만 이루어진 것(Html in JAVA)

JSP(Java Server Page) - html기반에 JAVA코드를 블록화하여 삽입한 것(JAVA in Html)





### 6. JDBC

Java Data Base Connection의 약자로 JAVA 언어를 통해 데이터 베이스에 접근 할 수 있는 프로그래밍을 의미





7. Get과 Post 방식

Get 방식

- 클라이언트에서 서버로 데이터를 전달할 때, 주소 뒤에 "이름"과 "값"이 결합된 스트링 형태로 전달

- 주소창에 쿼리 스트링이 그대로 보여지기 때문에 보안성이 떨어진다.

- 길이에 제한이 있다.(=전송 데이터의 한계가 있다.)

- Post방식보다 상대적으로 전송 속도가 빠르다.



Post 방식

- 일정 크기 이상의 데이터를 보내야 할 때 사용한다.

- 서버로 보내기 전에 인코딩하고, 전송 후 서버에서는 다시 디코딩 작업을 한다.

- 주소창에 전송하는 데이터의 정보가 노출되지 않아 Get방식에 비해 보안성이 높다.

- 속도가 Get방식보다 느리다.

- 쿼리스트링(문자열) 데이터 뿐만 아니라, 라디오 버튼, 텍스트 박스 같은 객체들의 값도 전송가능.



Get과 Post 차이점

- Get은 주로 웹 브라우저가 웹 서버에 데이터를 요청할 때 사용

- Post는 웹 브라우저가 웹 서버에 데이터를 전달하기 위해 사용.

- Get을 사용하면 웹 브라우저에서 웹 서버로 전달되는 데이터가 인코딩되어 URL에 붙는다.

- Post방식은 전달되는 데이터가 보이지 않는다.

- Get방식은 전달되는 데이터가 255개의 문자를 초과하면 문제가 발생할 수 있다.

- 웹서버에 많은 데이터를 전달하기 위해서는 Post 방식을 사용하는 것이 바람직하다.



# 프로젝트 정리

### BIS
IoT 장비를 활용한 서울시 통합 버스 서비스 및 빅데이터 의사결정 시스템
어떤 툴 / 주요

IoT 장비


스마트시티로의 가장 기반이 되는 '공공와이파이의 확대'라는 화두에 초점을 맞추고, 이를 활용할 만한 플랫폼이 부재한다고 판단했고 관리자, 운전자, 승객의 의사결정 과정 전반에 도움을 주고자 했습니다.

기존 대중교통 어플리케이션과는