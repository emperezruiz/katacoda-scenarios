Vamos a implementar un microservicio llamado ***Sadr*** que expondrá la operación ***ping*** que devuelve un mensaje.
Utilizaremos **Maven** para su construcción, así en su pom tendremos como parent:

```xml
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>1.4.0.RELEASE</version>
</parent>
```

Vamos a incluir en el dependency management las dependencias de **Spring Cloud** para posteriormente integrar nuestro servicio con un servidor de configuración y un servicio de discovery (recordemos el [anterior post](http://enmilocalfunciona.io/arquitectura-microservicios-1/)).

```
<dependencyManagement>
	<dependencies>
		<dependency>
	<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-dependencies</artifactId>
			<version>Brixton.SR1</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```
También vamos a tunearlo un poco haciendo que corra bajo **Undertow** embebido en lugar de **Tomcat**. Conseguiremos de esta manera mejorar su rendimiento. Para ello tenemos que añadir la siguiente dependencia:
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-undertow</artifactId>
</dependency>
```
Y añadiremos también la dependencia de **[Spring Boot Actuator](http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html)** lo que nos permitirá monitorizar aspectos como salud y configuración de nuestra aplicación:

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
