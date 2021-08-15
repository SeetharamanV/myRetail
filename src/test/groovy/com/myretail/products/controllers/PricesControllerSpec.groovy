package com.myretail.products.controllers

import com.myretail.products.AbstractSpecification
import com.myretail.products.prices.controllers.PricesController
import com.myretail.products.prices.entities.AllPrices
import com.myretail.products.prices.entities.Price
import com.myretail.products.prices.entities.PricesDocument

class PricesControllerSpec extends AbstractSpecification {
    def pricesController = new PricesController()
    def "Items Controller test - get all items."() {
        given:
        def expectedPriceDocument = new PricesDocument(123, new AllPrices(new Price(BigDecimal.ONE.toDouble(), "USD"), null, null))

        when:
        def result = pricesController.getPricesByProductId(123)

        then:

        expectedPriceDocument == result

    }
}
