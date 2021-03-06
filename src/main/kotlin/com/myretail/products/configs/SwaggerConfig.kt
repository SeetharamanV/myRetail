package com.myretail.products.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
class SwaggerConfig {
    @Bean
    fun swaggerDocket(): Docket {
        val apiInfo = ApiInfoBuilder()
            .title("My Retail Products API")
            .description("My Retail Products API")
            .build()
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.myretail.products"))
            .build()
            .apiInfo(apiInfo)
    }
}
