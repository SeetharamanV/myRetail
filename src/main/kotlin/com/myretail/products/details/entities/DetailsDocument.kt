package com.myretail.products.details.entities

data class DetailsDocument(
    val productId: Long,
    val name: String? = null,
    val currentPrice: Price? = null
)

data class Price(
    val value: Double,
    val currencyCode: String
)
