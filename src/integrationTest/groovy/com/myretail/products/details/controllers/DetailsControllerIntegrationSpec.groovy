package com.myretail.products.details.controllers

import com.myretail.products.AbstractRestIntegrationSpecification
import org.springframework.test.web.servlet.MvcResult

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals

class DetailsControllerIntegrationSpec extends AbstractRestIntegrationSpecification {

    private static Long PRODUCT_ID = 123L
    private static String EXCLUDES = "parm1"

    def "Test Details Aggregation Controller - Get Happy "() {
        given:
        String priceResponse = """{
          "product_id": $PRODUCT_ID,
          "prices": {
            "current_price": {
              "value": 1.0,
              "currency_code": "USD"
            }
          }
        }"""
        stub(getPricesUrl(PRODUCT_ID), "GET", priceResponse, 200)

        and:
        String attributeResponse = """{
          "product_id": $PRODUCT_ID,
          "attributes": {
            "name": "Acme Glue",
            "department": "Home",
            "unit_of_measure": "pounds"
          }
        }"""
        stub(getAttributesUrl(PRODUCT_ID, EXCLUDES), "GET", attributeResponse, 200)

        and:
        def expectedResponse = """{
          "product_id": $PRODUCT_ID,
          "name": "Acme Glue",
          "current_price": {
            "value": 1.0,
            "currency_code": "USD"
          }
        }"""

        when:
        MvcResult result = mockGet(
                "/v1/details/products/$PRODUCT_ID?excludes=$EXCLUDES&key=testkey1",
                "Bearer `testtoken`"
            ).andReturn()

        then:
        result.response.status == 200
        assertJsonEquals(expectedResponse.toString(), result.response.contentAsString)

        and:
        1L == getWireMockCallCount(getPricesUrl(PRODUCT_ID), "GET")
        1L == getWireMockCallCount(getAttributesUrl(PRODUCT_ID, EXCLUDES), "GET")

        0 * _
    }

    def "Test Details Aggregation Controller - 403 "() {
        when:
        MvcResult result = mockGet("/v1/details/products/$PRODUCT_ID?key=testkey1","Bearer `token`").andReturn()

        then:
        result.response.status == 403
        result.response.contentAsString == "Invalid AUTHORIZATION TOKEN."

        and:
        0L == getWireMockCallCount(getPricesUrl(PRODUCT_ID), "GET")
        0L == getWireMockCallCount(getAttributesUrl(PRODUCT_ID), "GET")

        0 * _
    }

    def "Test Details Aggregation Controller - 401 "() {
        when:
        MvcResult result = mockGet("/v1/details/products/$PRODUCT_ID?key=key1","Bearer `testtoken`").andReturn()

        then:
        result.response.status == 401
        result.response.contentAsString == "Invalid API KEY."

        and:
        0L == getWireMockCallCount(getPricesUrl(PRODUCT_ID), "GET")
        0L == getWireMockCallCount(getAttributesUrl(PRODUCT_ID), "GET")

        0 * _
    }
}
