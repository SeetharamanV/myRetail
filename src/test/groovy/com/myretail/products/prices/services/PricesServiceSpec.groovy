package com.myretail.products.prices.services

import com.myretail.products.AbstractSpecification
import com.myretail.products.prices.entities.AllPrices
import com.myretail.products.prices.entities.PricesDocument
import com.myretail.products.prices.entities.PricesRequest
import com.myretail.products.prices.entities.PricesResponse
import com.myretail.products.prices.repositories.PricesRepository

class PricesServiceSpec extends AbstractSpecification {
    def pricesRepository = Mock(PricesRepository)
    def pricesService = new PricesService(pricesRepository)
    def "Prices Services test - get by product id - happy path."() {
        given:
        def pricesDocument = easyRandom.nextObject(PricesDocument)
        def expectedPriceResponse = pricesDocument.toPricesResponse()

        when:
        def result = pricesService.getPricesByProductId(123)

        then:
        expectedPriceResponse == result
        1 * pricesRepository.findByProductId(123) >> pricesDocument

        0 * _
    }

    def "Prices Services test - create prices for product id - happy path."() {
        given:
        def prices = easyRandom.nextObject(AllPrices)
        def request = new PricesRequest(prices)
        def priceDocument = new PricesDocument(null, 123, prices)
        def expectedPriceResponse = priceDocument.toPricesResponse()

        when:
        def result = pricesService.createPricesForProduct(123, request)

        then:
        expectedPriceResponse == result
        1 * pricesRepository.save(priceDocument) >> priceDocument

        0 * _
    }
}
