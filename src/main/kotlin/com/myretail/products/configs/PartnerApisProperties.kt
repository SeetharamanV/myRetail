package com.myretail.products.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "myretail.partner-apis")
data class PartnerApisProperties(
    val apiKey: String,
    val authorizationToken: String,
    val prices: UrlProperties,
    val attributes: UrlProperties
)

data class UrlProperties(
    val baseUrl: String,
    val path: String,
    val timeout: Long = 10L
)
