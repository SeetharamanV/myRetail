package com.myretail.products.prices.services

import com.myretail.products.prices.entities.PricesDocument
import com.myretail.products.prices.entities.PricesRequest
import com.myretail.products.prices.entities.PricesResponse
import com.myretail.products.prices.repositories.PricesRepository
import org.springframework.stereotype.Service

@Service
class PricesService(private val pricesRepository: PricesRepository) {
    fun getPricesByProductId(productId: Long): PricesResponse? {
        return pricesRepository.findByProductId(productId)?.toPricesResponse()
    }

    fun createPricesForProduct(
        productId: Long,
        pricesRequest: PricesRequest
    ): PricesResponse? {
        return pricesRepository.save(PricesDocument(productId = productId, prices = pricesRequest.prices)).toPricesResponse()
    }
}
