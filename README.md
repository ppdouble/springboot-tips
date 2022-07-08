### ENV
spring boot 2.6.0

## UnitTest
```xml
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<scope>test</scope>
		</dependency>
```

```java
@RunWith(SpringJUnit4ClassRunner.class) // @RunWith: integrate spring with junit
@SpringBootTest(classes = {PemoApplication.class}) // @SpringBootTest: this class is spring boot test.
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void testGetUser() {
        Assert.assertEquals(userService.getUser(), "Tom");
    }
}
```

## Logging
Spring boot `logback`

`application.properties`
```yaml
debug=true
logging.level.root=DEBUG
logging.file.path=/var/log
logging.file.name=myspringboot.log
```

```java
@RestController
public class LoggingController {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping("/log")
    public Map<String, Object> hellolog() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", "Stephen");
        logger.info("this is a info message");
        logger.warn("this is a warn message");
        logger.error("this is an error message");
        return result;
    }
}
```

## Validation 
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
```

## Hotswap

1. maven

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
```
Then you can run project using `./mvnw spring-boot:run`. And when you modify thymeleaf templates or other classes of the project, the modified part will be reloaded automatically.

2. IEDA

If you want to run the project in IDEA, you should make the checkbox checked for IDEA 2021.2.
`Settings` -> `Advanced Settings` -> `Allow auto-make to start even if developed application is currently running`

## thymeleaf

`application.properties`

```yaml
spring.thymeleaf.enabled=true
spring.thymeleaf.check-template-location=true
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.mode=HTML
spring.thymeleaf.cache=false
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.suffix=.html
```

## Exception
**Method 1**

`@ControllerAdvice` and `@ExceptionHandler`

```java
@ControllerAdvice //to write global code that can be applied to a wide range of controllers
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {java.lang.ArithmeticException.class})
    public ModelAndView handleArithmeticException(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exceptionmsg", "ArithmeticException from GlobalExceptionHandler with ControllerAdvice");
        modelAndView.setViewName("exceptions");
        return modelAndView;
    }

    @ExceptionHandler(value = {java.lang.NullPointerException.class})
    public ModelAndView handleNullPointerException(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exceptionmsg", "NullPointerException from GlobalExceptionHandler with ControllerAdvice");
        modelAndView.setViewName("exceptions");
        return modelAndView;
    }
}
```

**Method 2**

`@Configuration`, `@Bean` and `SimpleMappingExceptionResolver`
```java
@Configuration
public class MySimpleMappingExceptionResolver {

    @Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties propertiesMap = new Properties();
        // Exception name and View name
        propertiesMap.put("java.lang.ArithmeticException", "exceptionsresolver");
        propertiesMap.put("java.lang.NullPointerException", "exceptionsresolver");  // Is it a bug for multi-exceptions program with only one view? The view name can be various for different exceptions.
        simpleMappingExceptionResolver.setExceptionMappings(propertiesMap);

        // default attribute name is "exception"
        return simpleMappingExceptionResolver;
    }
}
```

**Method 3**

`Configuration` and `HandlerExceptionResolver`

```java
@Configuration
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, @Nullable Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        if (e instanceof ArithmeticException) {
            modelAndView.setViewName("exceptionsresolver");
        }
        if (e instanceof NullPointerException) {
            modelAndView.setViewName("exceptionsresolver");
        }
        modelAndView.addObject("exception", e.toString() + " from HandlerExceptionResolver");
        return modelAndView;
    }
}
```

priority

Method 1 > Method 2 > Method 3

