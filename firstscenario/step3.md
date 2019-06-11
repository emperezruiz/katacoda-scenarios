####Configuración
Tendremos el siguiente fichero de configuración:

* **application.properties**:
```
# HTTP Server
server:
  port: 3333   # HTTP port

# Spring properties
spring:
  application:
    name: sadr-service

logging:
  file: logs/${spring.application.name}.log
  level:
    org.springframework.cloud: 'DEBUG'
    com.atsistemas: 'DEBUG'
```
La property ***server.port*** indica el puerto donde el servicio escuchará y ***spring.application.name*** el nombre de la aplicación.

Con esto ya tenemos listo nuestro microservicio. Para arrancarlo:
`mvn spring-boot:run`

Podemos probarlo mediante la interface de **swagger-ui**:
http://localhost:3333/swagger-ui.html
<div class="image-div" style="position: relative; width: 70%;margin-left:15%">
![](/content/images/2016/08/sadr_swagger-ui.jpg)
</div>

