package com.myretail.products.prices.controllers

import com.myretail.products.AbstractRestIntegrationSpecification
import org.springframework.test.web.servlet.MvcResult

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals

class PricesControllerIntegrationSpec extends AbstractRestIntegrationSpecification {

    def "Test Price Core Controller - Get Happy "() {
        given:
        saveTestPrices()
        String expectedResponse = """{
            "product_id":$PRODUCT_ID,
            "prices":{
                "current_price":{
                    "value":1.99,
                    "currency_code":"USD"
                },
                "regular_price":{
                    "value":1.99,
                    "currency_code":"USD"
                },
                "initial_price":{
                    "value":1.99,
                    "currency_code":"USD"
                }
            }
        }""" as String

        when:
        MvcResult result = mockGet("/v1/prices/products/$PRODUCT_ID?key=testkey1","Bearer `testtoken`").andReturn()

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
