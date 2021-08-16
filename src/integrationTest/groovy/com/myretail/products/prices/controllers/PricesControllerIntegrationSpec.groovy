package com.myretail.products.prices.controllers

import com.myretail.products.AbstractRestIntegrationSpecification
import org.springframework.test.web.servlet.MvcResult

class PricesControllerIntegrationSpec extends AbstractRestIntegrationSpecification {

    def "Test Price Core Controller - Get Happy "() {
        given:
        String expectedResponse = """{"product_id":1233,"prices":{"current_price":{"value":1.0,"currency_code":"USD"}}}""" as String

        when:
        MvcResult result = mockGet("/v1/prices/products/1233").andReturn()

        then:
        result.response.status == 200
        result.response.contentAsString == expectedResponse
    }

    def "Test Price Core Controller - Bad Request "() {
        when:
        MvcResult result = mockGet("/v1/prices/products/ABC").andReturn()

        then:
        result.response.status == 400
    }
}
