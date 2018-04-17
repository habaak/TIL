# R과 Spring MVC 연동
필요한 jar file
  1. REngine.jar
  2. RserveEngine.jar
  3. Json-simple-1.1.1.jar

### 1. Rserve
Rserve는 GPL License를 가지고 있는 R의 라이브러리로서 설치를 하면 Java, C, C++, PHP와 같은 다른 프로그램에서 TCP/IP로 R에 원격 접속, 인증, 파일전송을 가능하게 해준다. Rserve는 최근까지도 최신버젼이 지속적으로 업데이트가 되고 있다.

접속
  ```
  #로컬접속
  Rserve()
  #리모트 접속  
  Rserve(args="--RS-enable-remote")
  new RConnection("ip")

  #demon에서 실행
run.Rserve()
  ```
한글문제 해결
  ```
  RConnection rconn = null;
rconn = new RConnection();

rconn.setStringEncoding("utf8")

rconn.eval("source('C:/r/d1/jdbc.R',encoding='UTF-8')");
  ```

  ### 2. JAVA
  RConnection
