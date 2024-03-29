package com.atsistemas.katacoda.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
class SwaggerConfig {

    /**
     * Create Swagger Api configuration
     *
     * @return Swagger Docket
     */
    @Bean
    public Docket serviceApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("katacoda")
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
                .title("Katacoda My first MicroService API")
                .description("Katacoda My first MicroService API")
                .version("0.1-SNAPSHOT")
                .termsOfServiceUrl("https://github.com/sdcuike/spring-boot-practice")
                .license("Open source licensing")
                .licenseUrl("https://help.github.com/articles/open-source-licensing/")
                .build();
    }
}