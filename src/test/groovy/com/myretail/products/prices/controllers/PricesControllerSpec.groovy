package com.myretail.products.prices.controllers

import com.myretail.products.AbstractSpecification
import com.myretail.products.prices.entities.AllPrices
import com.myretail.products.prices.entities.Price
import com.myretail.products.prices.entities.PricesDocument

class PricesControllerSpec extends AbstractSpecification {
    def pricesController = new PricesController()
    def "Prices Controller test - get by product id - happy path."() {
        given:
        def expectedPriceDocument = new PricesDocument(123, new AllPrices(new Price(BigDecimal.ONE.toDouble(), "USD"), null, null))

        when:
        def result = pricesController.getPricesByProductId(123)

        then:
        expectedPriceDocument == result

        0 * _
    }
}
