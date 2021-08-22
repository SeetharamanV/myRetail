package com.myretail.products.details.controllers

import com.myretail.products.AbstractRestIntegrationSpecification
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Ignore

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals

class DetailsControllerIntegrationSpec extends AbstractRestIntegrationSpecification {

    def "Test Details Aggregation Controller - Get Happy "() {
        given:
        def productId = 123L
        String priceResponse = """{
          "product_id": $productId,
          "prices": {
            "current_price": {
              "value": 1.0,
              "currency_code": "USD"
            }
          }
        }"""
        stub(getPricesUrl(productId), "GET", priceResponse, 200)

        and:
        String attributeResponse = """{
          "product_id": $productId,
          "attributes": {
            "name": "Acme Glue",
            "department": "Home",
            "unit_of_measure": "pounds"
          }
        }"""
        stub(getAttributesUrl(productId), "GET", attributeResponse, 200)

        and:
        def expectedResponse = """{
          "product_id": $productId,
          "name": "Acme Glue",
          "current_price": {
            "value": 1.0,
            "currency_code": "USD"
          }
        }"""

        when:
        MvcResult result = mockGet(
                "/v1/details/products/$productId?key=testkey1",
                "Bearer `testtoken`"
            ).andReturn()

        then:
        result.response.status == 200
        assertJsonEquals(expectedResponse.toString(), result.response.contentAsString)
    }

    def "Test Details Aggregation Controller - 403 "() {
        when:
        MvcResult result = mockGet("/v1/details/products/123?key=testkey1","Bearer `token`").andReturn()

        then:
        result.response.status == 403
        result.response.contentAsString == "Invalid AUTHORIZATION TOKEN."
    }

    def "Test Details Aggregation Controller - 401 "() {
        when:
        MvcResult result = mockGet("/v1/details/products/123?key=key1","Bearer `testtoken`").andReturn()

        then:
        result.response.status == 401
        result.response.contentAsString == "Invalid API KEY."
    }
}
