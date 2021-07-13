package com.mathworks.ci;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/"},
        plugin = { "pretty",  "html:cucumber.html" ,"json:target/cucumber.json" },
        glue = {"com.mathworks.ci"})
public class TestsRunnerTest {
}