package com.opense.traininggain.cucumber;

import com.opense.traininggain.TrainingGainApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@SpringBootTest
@CucumberOptions(plugin = {"pretty"},features = "src/test/resources/features")
public class CucumberIT{

}
