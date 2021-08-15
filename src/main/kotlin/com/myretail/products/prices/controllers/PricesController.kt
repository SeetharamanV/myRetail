package com.myretail.products.prices.controllers

import com.myretail.products.prices.entities.AllPrices
import com.myretail.products.prices.entities.Price
import com.myretail.products.prices.entities.PricesDocument
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping(path = ["/v1"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PricesController {

    @GetMapping("/products/{productId}/prices")
    fun getPricesByProductId(
        @PathVariable productId: Long
//        ,@RequestParam("includes", defaultValue = "[]") includes: List<PriceType>
    ): PricesDocument {
        return PricesDocument(productId, AllPrices(Price(BigDecimal.ONE.toDouble(), "USD"), null, null))
    }
}
