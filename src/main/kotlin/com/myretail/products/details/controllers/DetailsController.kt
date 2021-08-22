package com.myretail.products.details.controllers

import com.myretail.products.details.entities.DetailsDocument
import com.myretail.products.details.services.DetailsService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/details"], produces = [MediaType.APPLICATION_JSON_VALUE])
class DetailsController(private val detailsService: DetailsService) {

    @GetMapping("/products/{productId}")
    fun getDetailsByProductId(
        @PathVariable productId: Long
    ): DetailsDocument {
        return detailsService.getDetailsForProductId(productId)
//        return DetailsDocument(productId, "Acme Glue", Price(BigDecimal.ONE.toDouble(), "USD"))
    }
}
