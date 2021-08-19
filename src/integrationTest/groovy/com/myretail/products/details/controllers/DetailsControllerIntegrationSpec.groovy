package com.myretail.products.details.controllers

import com.myretail.products.AbstractRestIntegrationSpecification
import org.springframework.test.web.servlet.MvcResult

class DetailsControllerIntegrationSpec extends AbstractRestIntegrationSpecification {

    def "Test Details Aggregation Controller - Get Happy "() {
        given:
        String expectedResponse = """{"product_id":1233,"name":"Acme Glue","current_price":{"value":1.0,"currency_code":"USD"}}""" as String

        when:
        MvcResult result = mockGet("/v1/details/products/1233?key=testkey1","Bearer `testtoken`").andReturn()

        then:
        result.response.status == 200
        result.response.contentAsString == expectedResponse
    }

    def "Test Details Aggregation Controller - Bad Request "() {
        when:
        MvcResult result = mockGet("/v1/details/products/ABC?key=testkey1","Bearer `testtoken`").andReturn()

        then:
        result.response.status == 400
    }
}
