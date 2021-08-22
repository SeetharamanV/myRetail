package com.myretail.products.prices.controllers

import com.myretail.products.AbstractSpecification
import com.myretail.products.prices.entities.PricesRequest
import com.myretail.products.prices.entities.PricesResponse
import com.myretail.products.prices.services.PricesService

class PricesControllerSpec extends AbstractSpecification {
    def pricesService = Mock(PricesService)
    def pricesController = new PricesController(pricesService)
    def "Prices Controller test - get by product id - happy path."() {
        given:
        def expectedPriceResponse = GroovyMock(PricesResponse)

        when:
        def result = pricesController.getPricesByProductId(123)

        then:
        expectedPriceResponse == result
        1 * pricesService.getPricesByProductId(123) >> expectedPriceResponse

        0 * _
    }

    def "Prices Controller test - create prices for product id - happy path."() {
        given:
        def request = GroovyMock(PricesRequest)
        def expectedPriceResponse = GroovyMock(PricesResponse)

        when:
        def result = pricesController.createPricesForProductId(123, request)

        then:
        expectedPriceResponse == result
        1 * pricesService.createPricesForProduct(123, request) >> expectedPriceResponse

        0 * _
    }
}
