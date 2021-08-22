package com.myretail.products.details.services

import com.myretail.products.details.clients.ProductAttributesClient
import com.myretail.products.details.entities.AttributesResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AttributesCoreService(private val attributesClient: ProductAttributesClient) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getAttributesForProductId(productId: Long, excludes: String = ""): AttributesResponse {
        val response = attributesClient.getAttributesByProductId(productId, excludes).execute()
        logger.info("$response")
        if (response.isSuccessful && null != response.body()) {
            return response.body()!!
        }

        throw Exception("Attributes Exception!")
    }
}
