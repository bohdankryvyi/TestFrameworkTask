package org.example.cucumber;



import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resources\\page.feature")
public class TestRunner {
// test runner that call page.feature file (with steps) and run StepDefinition class with those steps implementation
}
