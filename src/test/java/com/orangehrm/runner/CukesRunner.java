package com.orangehrm.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/login.feature",
        glue =  "com/orangehrm/step_definitions",
        dryRun = false,
        tags = "@positive",
        plugin = {
                "html:target/default-report",
                "json:target/cucumber.json",
                "rerun:target/ilhan.txt"
        }


)






public class CukesRunner {

}
