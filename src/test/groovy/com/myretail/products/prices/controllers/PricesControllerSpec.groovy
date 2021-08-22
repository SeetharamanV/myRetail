package com.myretail.products.prices.controllers

import com.myretail.products.AbstractSpecification
import com.myretail.products.prices.entities.AllPrices
import com.myretail.products.prices.entities.Price
import com.myretail.products.prices.entities.PricesDocument
import com.myretail.products.prices.repositories.PricesRepository

class PricesControllerSpec extends AbstractSpecification {
    def pricesRepository = Mock(PricesRepository)
    def pricesController = new PricesController(pricesRepository)
    def "Prices Controller test - get by product id - happy path."() {
        given:
        def priceDocument = new PricesDocument(
                null,
                123L,
                new AllPrices(
                    new Price(BigDecimal.ONE.toDouble(), "USD"),
                    null,
                    null
                )
        )
        def expectedPriceResponse = priceDocument.toPricesResponse()

        when:
        def result = pricesController.getPricesByProductId(123)

        then:
        expectedPriceResponse == result
        1 * pricesRepository.findByProductId(123) >> priceDocument

        0 * _
    }
}
