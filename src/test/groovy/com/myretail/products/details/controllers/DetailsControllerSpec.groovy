package com.myretail.products.details.controllers

import com.myretail.products.AbstractSpecification
import com.myretail.products.details.entities.DetailsDocument
import com.myretail.products.details.entities.Price

class DetailsControllerSpec extends AbstractSpecification {
    def detailsController = new DetailsController()
    def "Details Controller test - get by product id - happy path."() {
        given:
        def expectedDetailDocument = new DetailsDocument(123, "Acme Glue", new Price(BigDecimal.ONE.toDouble(), "USD"))

        when:
        def result = detailsController.getDetailsByProductId(123)

        then:
        expectedDetailDocument == result
    }
}
