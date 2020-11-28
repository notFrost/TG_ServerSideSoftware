package com.opense.traininggain.cucumber.glue;

import com.opense.traininggain.domain.repository.UserRepository;
import com.opense.traininggain.domain.service.UserService;
import com.opense.traininggain.service.UserServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

public class UserStepsDefinition
{
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @TestConfiguration
    static class UserStepsDefinitionConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Test
    @Given("the following user")
    public void givenTheFollowingUsers() {
            
    }

    @Test
    @When("the client request the user")
    public void whenTheClientRequestAllTheUsers(){

    }

    @Test
    @Then("^return the user")
    public void thenAllTheUsersAreReturned() {

    }

}
