package com.myretail.products

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.BootstrapWith
import spock.lang.Specification

@BootstrapWith(SpringBootTestContextBootstrapper)
@SpringBootTest(classes = [MyRetailApplication])
@ActiveProfiles(profiles = 'integration')
@AutoConfigureMockMvc
abstract class AbstractIntegrationSpecification extends Specification {
}
