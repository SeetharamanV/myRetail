package com.myretail.products.details.services

import org.springframework.retry.annotation.EnableRetry
import org.springframework.stereotype.Component

@EnableRetry
@Component
class PricesServicesClient() {
    fun getPricesForProductId(productId: Long) {
    }
}
