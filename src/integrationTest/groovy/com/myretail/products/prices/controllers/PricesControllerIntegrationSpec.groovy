package com.myretail.products.prices.controllers

import com.myretail.products.AbstractRestIntegrationSpecification
import org.springframework.test.web.servlet.MvcResult

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals

class PricesControllerIntegrationSpec extends AbstractRestIntegrationSpecification {

    def "Test Price Core Controller - Get Happy "() {
        given:
        String expectedResponse = """{
            "product_id":1233,
            "prices":{
                "current_price":{
                    "value":1.0,
                    "currency_code":"USD"
                }
            }
        }""" as String

        when:
        MvcResult result = mockGet("/v1/prices/products/1233?key=testkey1","Bearer `testtoken`").andReturn()

        then:
        result.response.status == 200
        assertJsonEquals(expectedResponse.toString(), result.response.contentAsString)

        0 * _
    }

    def "Test Price Core Controller - Bad Request "() {
        when:
        MvcResult result = mockGet("/v1/prices/products/ABC?key=testkey1","Bearer `testtoken`").andReturn()

        then:
        result.response.status == 400

        0 * _
    }
}
