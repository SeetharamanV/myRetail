package com.myretail.products.prices.controllers

import com.myretail.products.AbstractRestIntegrationSpecification
import org.springframework.test.web.servlet.MvcResult

class PricesControllerIntegrationSpec extends AbstractRestIntegrationSpecification {

    def "Test Price Core Controller - Get Happy "() {
        given:
        String expectedResponse = """{"product_id":1233,"prices":{"current_price":{"value":1.0,"currency_code":"USD"},"regular_price":null,"initial_price":null}}""" as String

        when: "Get prices for an item, happy path."
        MvcResult result = mockGet("/v1/products/1233/prices").andReturn()

        then:
        result.response.status == 200
        result.response.contentAsString == expectedResponse
    }

    def "Test Price Core Controller - Bad Request "() {
        when: "Get an item, bad request."
        MvcResult result = mockGet("/v1/products/ABC/prices").andReturn()

        then:
        result.response.status == 400
    }
}
