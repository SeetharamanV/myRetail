package com.myretail.products.details.services

import com.myretail.products.details.entities.DetailsDocument
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class DetailsService(private val pricesCoreService: PricesCoreService, private val attributesCoreService: AttributesCoreService) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getDetailsForProductId(productId: Long): DetailsDocument {
        val prices = pricesCoreService.getPricesForProductId(productId).prices
        val attributes = attributesCoreService.getAttributesForProductId(productId).attributes
        return DetailsDocument(productId, attributes.name, prices.currentPrice)
    }
}
