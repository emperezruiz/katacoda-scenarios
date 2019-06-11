A continuación vamos a ver la implementación y la configuración de nuestro servicio. Todo el código puede descargarse desde el repositorio [cygnus](https://github.com/atSistemas/cygnus.git)

####Implementación
<div class="image-div" style="position: relative; width: 70%;margin-left:15%">
![](/content/images/2016/08/sadr_eclipse-1.jpg)
</div>
Como aplicación **Spring Boot** tendremos una clase main que anotaremos con ***@SpringBootApplication***. Recordemos que con esta anotación ya no son necesarias ***@Config*** y ***@Autoconfiguration***

* **Application.java**
```
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}	
}
```
Añadiremos un controlador de **Spring MVC** que anotaremos con las anotaciones de [**Swagger**](http://swagger.io/) para definir nuestro API:

* **SadrController.java**
```
@Api
@RestController
@RequestMapping("sadr/")
public class SadrController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.POST, value = "ping/")
	@ApiOperation(value = "ping", nickname = "ping", response = PingResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
			@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public PingResponse ping(
			@ApiParam(value = "request", required = true) @RequestBody(required = true) PingRequest request) {

		logger.debug("--> ping received");
		logger.debug("--> id: {}", request.getId());
		logger.debug("--> content: {}", request.getMessage());

		return new PingResponse("Hello from Sadr - " + request.getId() + " - " + request.getMessage());
	}
}
```
Aprovechamos e incluimos las dependencias de **Swagger**. Hemos utilizado las librerías de **Springfox** ya que integra muy bien **Swagger** con **Spring MVC**:
```
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>2.4.0</version>
</dependency>
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>2.4.0</version>
</dependency>
```
Añadimos una clase de configuración para Swagger:

* **SwaggerConfig.java**
```
@Configuration
@EnableSwagger2
class SwaggerConfig {

    /**
     * Create Swagger Api configuration
     *
     * @return Swagger Docket
     */
    @Bean
    public Docket sadrApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("cygnus")
                .apiInfo(apiInfo())
                .select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                    .paths(PathSelectors.any())
                    .build()
                .pathMapping("/")
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false);
    }

    /**
     * Generate Api Info
     *
     * @return Swagger API Info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Sadr")
                .description("Gamma Cygni")
                .version("0.1-SNAPSHOT")
                .termsOfServiceUrl("https://es.wikipedia.org/wiki/Sadr")
                .license("Open source licensing")
                .licenseUrl("https://help.github.com/articles/open-source-licensing/")
                .build();
    }
}
```
Hubiera sido interesante (a la par que elegante) el poder desacoplar las anotaciones de **Swagger** del controlador por ejemplo en una interface, de esta manera quedaría más limpio el código del controlador, pero hasta la fecha, **Swagger** no lo soporta.
