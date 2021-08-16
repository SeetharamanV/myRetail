import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.jlleitschuh.gradle.ktlint") version "9.4.1"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
    kotlin("plugin.jpa") version "1.4.10"
    jacoco
    groovy
    application
}

group = "com.myretail"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    jcenter()
}

application {
    mainClass.set("com.myretail.products.MyRetailApplicationkt")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-core")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("org.codehaus.groovy:groovy:3.0.5")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.spockframework:spock-core:2.0-M3-groovy-3.0")
    testImplementation("org.spockframework:spock-spring:2.0-M3-groovy-3.0")
    testImplementation("org.jeasy:easy-random-core:4.2.0")
}

repositories {
    mavenCentral()
    jcenter()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

sourceSets {
    create("integrationTest") {
        withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")
            compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
            runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "failed")
    }
    outputs.upToDateWhen { false }
    finalizedBy(tasks.jacocoTestReport)
}

task<Test>("integrationTest") {
    description = "Runs the integration tests"
    group = "verification"
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    shouldRunAfter(tasks["test"])
    maxHeapSize = "1G"
    testLogging {
        events("passed", "failed")
    }
}

jacoco {
    toolVersion = "0.8.5"
    reportsDir = file("$buildDir/customJacocoReportDir")
}

tasks.withType<JacocoReportBase> {
    executionData(tasks["test"], tasks["integrationTest"])
    afterEvaluate {
        classDirectories.setFrom(
            sourceSets.main.get().output.asFileTree.matching {
                exclude(
                    "**/configs/**",
                    "**/entities/**",
                    "**/exceptions/**",
                    "**/models/**",
                    "**/preloads/**",
                    "**/repositories/**",
                    "**/MyRetailApplication**"
                )
            }
        )
    }
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.destination = file("$buildDir/jacocoHtml")
    }
    doLast {
        println("View code coverage at: file://$buildDir/jacocoHtml/index.html")
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            enabled = true
            limit {
                minimum = "0.6".toBigDecimal()
            }
        }
        rule {
            enabled = true
            element = "CLASS"
            includes = listOf("org.gradle.*")

            limit {
                counter = "LINE"
                value = "TOTALCOUNT"
                maximum = "0.6".toBigDecimal()
            }
        }
    }
}

tasks.ktlintKotlinScriptCheck {
    exclude { true }
}

tasks.check {
    dependsOn(tasks["integrationTest"])
    dependsOn(tasks.jacocoTestCoverageVerification)
    dependsOn(tasks.jacocoTestReport)
}

tasks.withType<AbstractArchiveTask> {
    setProperty("archiveFileName", "myretail.jar")
}