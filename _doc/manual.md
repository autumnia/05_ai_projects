# Manual
```txt
gradle init

gradle build
java -jar build/libs/your-app-name.jar

gradle bootRun

```


## 예제 주소
```url
https://github.com/parkmaeil/askchat
https://github.com/parkmaeil/aispring
https://github.com/parkmaeil/imagegen
https://github.com/parkmaeil/voiceimage
https://github.com/parkmaeil/imagetextgen.git

```

## Reference
```url
https://docs.spring.io/spring-ai/reference
https://platform.openai.com/docs/overview
```

## dependencies 추가
```gradle
org.springframework.ai:spring-ai-openai-spring-boot-starter
```

## 키발급
```tx
```


## pom.xml 추가사항
```xml
<dependencies>
    <dependency>
        <groupId>net.logstash.logback</groupId>
        <artifactId>logstash-logback-encoder</artifactId>
        <version>7.4</version>
    </dependency>
</dependencies>


<!-- 테스트 코드 실행 제외 -->
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.0.0-M7</version>
        <configuration>
            <skipTests>true</skipTests>
        </configuration>
    </plugin>
    
    <!-- 테스트 클래스를 JAR에 포함하지 않도록 설정 -->
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>3.2.0</version>
        <configuration>
            <excludes>
                <exclude>**/Test*.class</exclude>
                <exclude>**/*Test.class</exclude>
                <exclude>**/*Tests.class</exclude>
            </excludes>
        </configuration>
    </plugin>
</plugins>
```

