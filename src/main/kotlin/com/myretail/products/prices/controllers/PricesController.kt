package com.myretail.products.prices.controllers

import com.myretail.products.prices.entities.PricesResponse
import com.myretail.products.prices.repositories.PricesRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/prices"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PricesController(private val pricesRepository: PricesRepository) {

    @GetMapping("/products/{productId}")
    fun getPricesByProductId(
        @PathVariable productId: Long
//        ,@RequestParam("includes", defaultValue = "[]") includes: List<PriceType>
    ): PricesResponse? {
        return pricesRepository.findByProductId(productId)?.toPricesResponse()
    }
}
