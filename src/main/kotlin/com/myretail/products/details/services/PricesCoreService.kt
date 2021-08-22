package com.myretail.products.details.services

import com.myretail.products.details.clients.ProductPricesClient
import com.myretail.products.details.entities.PricesResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class PricesCoreService(private val pricesClient: ProductPricesClient) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getPricesForProductId(productId: Long): PricesResponse {
        val response = pricesClient.getPricesByProductId(productId).execute()
        logger.info("$response")
        if (response.isSuccessful && null != response.body()) {
            return response.body()!!
        }

        throw Exception("Price Exception!")
    }
}
