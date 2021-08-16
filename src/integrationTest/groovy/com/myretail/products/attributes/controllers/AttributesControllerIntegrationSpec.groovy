package com.myretail.products.attributes.controllers

import com.myretail.products.AbstractRestIntegrationSpecification
import org.springframework.test.web.servlet.MvcResult

class AttributesControllerIntegrationSpec extends AbstractRestIntegrationSpecification {

    def "Test Attributes Core Controller - No 'exclude' passed, returns everything "() {
        given:
        String expectedResponse = """{"product_id":1233,"attributes":{"name":"Acme Glue","department":"Home","unit_of_measure":"pounds"}}""" as String

        when: "Get attributes for an product"
        MvcResult result = mockGet("/v1/attributes/products/1233").andReturn()

        then:
        result.response.status == 200
        result.response.contentAsString == expectedResponse
    }

    def "Test Attributes Core Controller - Bad 'exclude' attribute passed "() {
        given:
        String expectedErrorResponse = """{"message":"Invalid attribute type passed for 'exclude'.","code":"PA-4000"}""" as String

        when:
        MvcResult result = mockGet("/v1/attributes/products/1233?excludes=test").andReturn()

        then:
        result.response.status == 400
        result.response.contentAsString == expectedErrorResponse
    }

    def "Test Attributes Core Controller - 'exclude' name and department "() {
        given:
        String expectedResponse = """{"product_id":1233,"attributes":{"unit_of_measure":"pounds"}}""" as String

        when:
        MvcResult result = mockGet("/v1/attributes/products/1233?excludes=name, department").andReturn()

        then:
        result.response.status == 200
        result.response.contentAsString == expectedResponse
    }
}
