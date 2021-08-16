package com.myretail.products.prices.entities

data class PricesDocument(
    val productId: Long,
    val prices: AllPrices
)

data class AllPrices(
    val currentPrice: Price?,
    val regularPrice: Price?,
    val initialPrice: Price?,
)

data class Price(
    val value: Double,
    val currencyCode: String
)
