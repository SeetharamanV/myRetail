package com.myretail.products.prices.controllers

import com.myretail.products.prices.entities.PricesRequest
import com.myretail.products.prices.entities.PricesResponse
import com.myretail.products.prices.services.PricesService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/prices"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PricesController(private val pricesService: PricesService) {

    @GetMapping("/products/{productId}")
    fun getPricesByProductId(
        @PathVariable productId: Long
//        ,@RequestParam("includes", defaultValue = "[]") includes: List<PriceType>
    ): PricesResponse? {
        return pricesService.getPricesByProductId(productId)
    }

    @PostMapping("/products/{productId}")
    fun createPricesForProductId(
        @PathVariable productId: Long,
        @RequestBody pricesRequest: PricesRequest
    ): PricesResponse? {
        return pricesService.createPricesForProduct(productId, pricesRequest)
    }
}
