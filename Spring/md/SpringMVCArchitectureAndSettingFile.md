# Spring MVC 구조 및 설정 파일

**MVC 패턴?**
Model-View-Controller의 약자로, 사용자 인터페이스와 비지니스 로직을 분리하여 웹 개발을 하는것을 가장 큰 장점으로 한다.

Model : 모델은 애플리케이션의 정보, 즉 데이터를 나타낸다.

View : 뷰는 사용자에게 보여주는 인터페이스, 즉 화면을 이야기한다. 자바 웹 애플리케이션에서는 JSP를             의미한다.

Controller : 컨트롤러는 비지니스 로직과 모델의 상호동작의 조정 역할을 한다. MVC2에서는 서블릿이 흐름을 제어하는 컨트롤러 역할을 수행한다.
![MVC](http://cfile28.uf.tistory.com/image/2109C03A53CFB96A18FBEB)


### 1.Spring 라이브러리 추가
메이븐에서는 <dependency></dependency>라는 태그를 통해서 각 라이브러리를 추가할 수 있다.

여기서 추가해야할 라이브러리가 굉장히 많은 관계로 각 라이브러리에 대한 자세한 설명은 생략하고 바로 pom.xml만 먼저 확인하고, 복사해서 붙여넣는것을 권장한다.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.company</groupId>
    <artifactId>first</artifactId>
    <name>first</name>
    <packaging>war</packaging>
    <version>1.0.0-BUILD-SNAPSHOT</version>
    <properties>
        <java-version>1.7</java-version>
        <org.springframework-version>3.2.4.RELEASE</org.springframework-version>
        <org.aspectj-version>1.7.3</org.aspectj-version>
        <org.slf4j-version>1.6.6</org.slf4j-version>
    </properties>

    <repositories>
        <repository>
            <id>mvn2</id>
            <url>http://repo1.maven.org/maven2/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>

        <repository>
            <id>egovframe</id>
            <url>http://www.egovframe.go.kr/maven/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>

        <repository>
             <id>oracle</id>
             <name>ORACLE JDBC Repository</name>
             <url>http://mesir.googlecode.com/svn/trunk/mavenrepo</url>
        </repository>
    </repositories>

    <dependencies>
        <!-- Spring -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${org.springframework-version}</version>
            <exclusions>
                <!-- Exclude Commons Logging in favor of SLF4j -->
                <exclusion>
                    <groupId>commons-logging</groupId>
                    <artifactId>commons-logging</artifactId>
                 </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>

        <!-- AOP Alliance -->
        <dependency>
            <groupId>aopalliance</groupId>
            <artifactId>aopalliance</artifactId>
            <version>1.0</version>
        </dependency>

         <!-- MyBatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.2.2</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.2.0</version>
        </dependency>

        <!-- DBCP -->
        <dependency>
            <groupId>org.apache.tomcat</groupId>
            <artifactId>tomcat-dbcp</artifactId>
            <scope>provided</scope>
            <version>7.0.53</version>
        </dependency>

        <dependency>
            <groupId>commons-dbcp</groupId>
            <artifactId>commons-dbcp</artifactId>
            <version>1.4</version>
        </dependency>

        <!-- MySql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.31</version>
        </dependency>

        <!-- Oracle -->
        <dependency>
            <groupId>com.oracle</groupId>
            <artifactId>ojdbc14</artifactId>
            <version>10.2.0.4.0</version>
        </dependency>

        <!-- AspectJ -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjrt</artifactId>
            <version>${org.aspectj-version}</version>
        </dependency>

        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${org.aspectj-version}</version>
        </dependency>

        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjtools</artifactId>
            <version>${org.aspectj-version}</version>
        </dependency>

        <!-- Web -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <scope>provided</scope>
            <version>2.5</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <scope>provided</scope>
            <version>2.1</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <dependency>
            <groupId>javax.annotation</groupId>
            <artifactId>jsr250-api</artifactId>
            <version>1.0</version>
        </dependency>

        <!-- Logging -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${org.slf4j-version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jcl-over-slf4j</artifactId>
            <version>${org.slf4j-version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>${org.slf4j-version}</version>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.3alpha-8</version>
        </dependency>
        <dependency>
            <groupId>org.lazyluke</groupId>
            <artifactId>log4jdbc-remix</artifactId>
            <version>0.2.7</version>
        </dependency>

        <!-- MappingJacksonJsonView -->
        <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-mapper-asl</artifactId>
            <version>1.9.13</version>
        </dependency>

        <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-core-asl</artifactId>
            <version>1.9.13</version>
        </dependency>

        <!-- MultipartHttpServletRequset -->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.0.1</version>
        </dependency>

        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.2.2</version>
        </dependency>

        <!-- Apache Codec -->
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>1.10</version>
        </dependency>

        <!-- EgovProperty -->
        <dependency>
            <groupId>egovframework.rte</groupId>
            <artifactId>egovframework.rte.fdl.property</artifactId>
            <version>2.7.0</version>
        </dependency>

        <!-- Log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.15</version>
            <exclusions>
                <exclusion>
                    <groupId>javax.mail</groupId>
                    <artifactId>mail</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.jms</groupId>
                    <artifactId>jms</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.sun.jdmk</groupId>
                    <artifactId>jmxtools</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.sun.jmx</groupId>
                    <artifactId>jmxri</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <!-- @Inject -->
        <dependency>
            <groupId>javax.inject</groupId>
            <artifactId>javax.inject</artifactId>
            <version>1</version>
        </dependency>

        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>2.3</version>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.7</version>
            <scope>test</scope>
        </dependency>  
    </dependencies>


    <build>
        <defaultGoal>install</defaultGoal>
        <directory>${basedir}/target</directory>
        <finalName>first</finalName>
        <plugins>
            <plugin>
                <artifactId>maven-eclipse-plugin</artifactId>
                <version>2.9</version>
                <configuration>
                    <additionalProjectnatures>
                        <projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
                    </additionalProjectnatures>
                    <additionalBuildcommands>
                        <buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
                    </additionalBuildcommands>
                    <downloadSources>true</downloadSources>
                    <downloadJavadocs>true</downloadJavadocs>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.5.1</version>
                <configuration>
                    <source>${java-version}</source>
                    <target>${java-version}</target>
                    <encoding>UTF-8</encoding>
                    <compilerArgument>-Xlint:all</compilerArgument>
                    <showWarnings>true</showWarnings>
                    <showDeprecation>true</showDeprecation>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>1.2.1</version>
                <configuration>
                    <mainClass>org.test.int1.Main</mainClass>
                </configuration>
            </plugin>

            <!-- JavaDoc -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-javadoc-plugin</artifactId>
                <version>2.5</version>
            </plugin>
        </plugins>
    </build>
</project>
```
<org.springframework-version>3.2.4.RELEASE</org.springframework-version> 라는것을 볼 수 있는데, 이 글을 시작할때 Spring 3.2.4 버전을 사용하기 때문에, 이 값을 이렇게 선언하였다. 만약 스프링 버전을 변경하고 싶으면 이 변수만 바꿔주면 된다.

다음은 <repositories></repositories> 태그다.

이는 실제 라이브러리를 다운받을 저장소를 의미한다. 보통은 따로 설정할 필요가 없다. 하지만 프로젝트를 진행하다보면 인터넷에 연결할 수 없는 프로젝트도 상당히 많은데, 이럴때 내부 저장소를 만들어놓고, 개발자들은 내부저장소에서 라이브러리를 다운받도록 되어있다.

여기서는 인터넷에서 라이브러리를 받으면서, 추가로 전자정부 프레임워크의 기능 중 하나를 사용하기 위해서 전자정부 프레임워크 저장소도 같이 추가했다.

### 2.설정파일 변경 (web.xml, action-servlet.xml 등등)

##### 1. UTF-8 설정
다음을 web.xml에 추가한다.
```XML
<filter>
    <filter-name>encodingFilter</filter-name>
<filter-class>
        org.springframework.web.filter.CharacterEncodingFilter
</filter-class>
<init-param>
    <param-name>encoding</param-name>
    <param-value>utf-8</param-value>
</init-param>
</filter>
<filter-mapping>
    <filter-name>encodingFilter</filter-name>
<url-pattern>*.do</url-pattern>
</filter-mapping>
```

##### 2. 그 외 설정파일 경로 설정
기존 설정파일을 보면 <servlet>설정중에 <param-value>라는것이 있었다.

기존에는 servlet 설정파일이 action-servlet.xml 하나만 있었는데, 이제 또 추가되기 때문에, 확장성을 생각하여 특정 폴더에 있는 설정파일을 모두 읽어오는 방식으로 변경한다.

<param-value> 태그를 다음과 같이 수정한다.


```xml
<param-value>/WEB-INF/config/*-servlet.xml</param-value>
```


이는 /WEB-INF/config/ 폴더안에 있는 -servlet.xml로 끝나는 모든 파일을 읽어오는것을 의미한다. 따라서 앞으로 설정파일을 추가할때는 XXXXXX-servlet.xml로 만들게 되면, 자동적으로 설정파일을 읽어들인다.

다음으로는 Spring 설정파일을 추가한다. 기존에 <context-param> 태그의 <param-value>태그안에는 아마 아무것도 작성되지 않았을 것이다. 이제 본격적인 스프링 설정파일을 읽어오기 위해서 다음과 같이 바꾼다.


```xml
<param-value>classpath*:config/spring/context-*.xml</param-value>
```


이는 앞에서 설명한것과 비슷하게 context-로 시작하는 모든 .xml 을 읽어오는것을 뜻한다.

여기서 지금 이렇게 추가하면 context-XXXXXX.xml 파일이 없기 때문에 에러가 날 것이다. 일단 작성만 해 놓고 주석처리하도록 한다.

여기까지 작성해서 완성된 web.xml은 다음과 같다.
