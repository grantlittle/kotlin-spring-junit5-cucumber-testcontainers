package me.grantlittle.examples.kotlinspringjunit5cucumber

import io.cucumber.java.AfterAll
import io.cucumber.java.BeforeAll
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.platform.engine.Constants
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("me/grantlittle/examples/kotlinspringjunit5cucumber") // This selector is picked up by Cucumber
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "usage")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "html:target/cucumber-reports.html")
class EndToEndIntegrationTests

class StepDefinitions {
	@Given("a spring boot application")
	fun a_spring_boot_application() {

	}

	@When("the application starts")
	fun the_application_starts() {
	}

	@Then("the context should load successfully")
	fun the_context_should_load_successfully() {
	}
}


@SpringBootTest
@CucumberContextConfiguration
@Testcontainers
object Bootstrap {

	@Container
	@ServiceConnection
	var redisContainer: GenericContainer<*> = GenericContainer(DockerImageName.parse("redis:latest"))
		.withExposedPorts(6379)


}


@BeforeAll
fun setUp() {
	Bootstrap.redisContainer
		.withReuse(true)
		.start()

}

@AfterAll
fun tearDown() {
	Bootstrap.redisContainer.stop()
}