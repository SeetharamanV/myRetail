package com.myretail.products.prices.entities

import com.fasterxml.jackson.annotation.JsonInclude
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "prices")
data class PricesDocument(
    @Id val _id: ObjectId? = null,
    val productId: Long,
    val prices: AllPrices
) {
    fun toPricesResponse(): PricesResponse = PricesResponse(this.productId, this.prices)
}

data class PricesRequest(
    val productId: Long,
    val prices: AllPrices
) {
    fun toPriceDocument(): PricesDocument = PricesDocument(productId = this.productId, prices = this.prices)
}

data class PricesResponse(
    val productId: Long,
    val prices: AllPrices
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AllPrices(
    val currentPrice: Price? = null,
    val regularPrice: Price? = null,
    val initialPrice: Price? = null
)

data class Price(
    val value: Double,
    val currencyCode: String
)
