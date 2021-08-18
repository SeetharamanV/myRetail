package com.myretail.products

import com.myretail.products.security.SecurityProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(SecurityProperties::class)
class MyRetailApplication

fun main(args: Array<String>) {
    runApplication<MyRetailApplication>(*args)
}
