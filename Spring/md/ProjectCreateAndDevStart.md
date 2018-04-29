# 프로젝트 생성 및 개발 시작


### 1. 프로젝트의 구조
![Folder Tree](http://cfile10.uf.tistory.com/image/235C623553CD0B7220EEC5)

#### 2. index.jsp 추가
##### 1) webapp 우클릭 > new > JSP File을 선택
![index.jsp 파일 생성](http://cfile10.uf.tistory.com/image/235C623553CD0B7220EEC5)
##### 2) index.jsp 라는 이름으로 jsp파일 생성

index.jsp가 호출되었다는 것을 보기 위해, index.jsp의 body 안에 "This is index.jsp!"를 추가한다

```HTML
<body>
    <h1>This is index.jsp.</h1>
</body>
```

##### 3) web.xml 설정 변경
서블릿 설정을 통해서, 이 프로젝트가 시작되면 index.jsp를 호출하도록 바꾸겠다.

web.xml에 다음의 내용을 추가한다.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">

    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
```
이 위치는 web.xml의 <web-app></web-app> 태그 사이에 존재하면 된다. 즉, 이 태그는 web.xml의 아무곳에서나 선언하면된다. 여기서는 web.xml 맨 위에 위치하도록 하였다.

##### 4) 실행
서버를 실행시키고, 브라우저의 주소 창에 root url을 입력한다.


### 3. 서블릿 설정 변경
서블릿(Servlet) : 자바에서 동적 웹 프로젝트를 개발할 때, 사용자의 요청과 응답을 처리해 주는 역할을 한다.
보통 스프링에서는 servlet 설정이 .do로 되어있는데, 현재 기본 프로젝트에서는 .do로 되어있지 않다. 따라서, 서블릿 설정을 간단히 바꿔주려고 한다.

web.xml을 열어보면 다음과 같은 부분이 있다.

```xml
<servlet>
    <servlet-name>appServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>appServlet</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>

```
를 다음과 같이 바꾼다.
```xml
<servlet>
        <servlet-name>action</servlet-name>
        <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>
                /WEB-INF/config/*-servlet.xml
            </param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
        <servlet-name>action</servlet-name>
        <url-pattern>*.do</url-pattern>
</servlet-mapping>
```

**url-pattern** : '.do'를 통해서만 요청을 전달하고, 다른 방식의 요청, 예를 들어 .html의 직접적인 호출 등은 이제 허락되지 않는다.

**contextConfigLocation** : dispatcher 의 설정인 contextConfigLocation이 /WEB-INF/spring/appServlet/servlet-context.xml에 존재하였는데, 이를 /WEB-INF/config/action-servlet.xml로 변경하고, 인터셉터(Intercepter)도 추가하려는 목적이다.

**context-param** : <context-param>에서 설정되어있던 root-context.xml은 모든 서블릿과 필터에서 사용되는 루트 스프링 컨테이너에 대한 설정이다(The definition of the Root Spring Container shared by all Servlets and Filters).

이 root-context.xml은 추후 다른곳에서 설정될 예정이므로 <param-value></param-value>안에 있던 /WEB-INF/spring/root-context.xml 부분은 다음과 같이 지운다.

```xml
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value></param-value>
</context-param>
```

### 4.contextConfigLocation 변경
서블릿 설정 변경에서 기존의 servlet-context.xml의 이름을 action-servlet.xml로 바꾸고, 위치도 바꾸었다. 설정파일은 변경되었으니, 이제 실제 소스를 바꾸려고 한다.

1. 위에서 설정한 것처럼 /WEB-INF 디렉토리 밑에 config라는 폴더를 만든다.
![config폴더 생성](http://cfile7.uf.tistory.com/image/2448B53953C3E7090AFFB6)
2. /WEB-INF/spring/appServlet 디렉토리에 있는 servlet-context.xml을 복사해서, /WEB-INF/config 폴더에 붙여넣고, 이름을 action-servlet.xml로 변경한다.

3. /WEB-INF/에 있는 spring이라는 디렉토리를 삭제한다.
