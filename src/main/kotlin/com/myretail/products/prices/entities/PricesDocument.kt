package com.myretail.products.prices.entities

import com.fasterxml.jackson.annotation.JsonInclude

data class PricesDocument(
    val productId: Long,
    val prices: AllPrices
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AllPrices(
    val currentPrice: Price? = null,
    val regularPrice: Price? = null,
    val initialPrice: Price? = null,
)

data class Price(
    val value: Double,
    val currencyCode: String
)
