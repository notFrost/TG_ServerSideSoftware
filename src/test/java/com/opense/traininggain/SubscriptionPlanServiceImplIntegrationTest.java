package com.opense.traininggain;

import com.opense.traininggain.domain.model.SubscriptionPlan;
import com.opense.traininggain.domain.model.Tag;
import com.opense.traininggain.domain.repository.CustomerRepository;
import com.opense.traininggain.domain.repository.SubscriptionPlanRepository;
import com.opense.traininggain.domain.service.CustomerService;
import com.opense.traininggain.domain.service.SubscriptionPlanService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import com.opense.traininggain.service.CustomerServiceImpl;
import com.opense.traininggain.service.SubscriptionPlanServiceImpl;
import io.cucumber.java.bs.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SubscriptionPlanServiceImplIntegrationTest {
    @MockBean
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @MockBean
    private CustomerRepository customerRepository;
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @TestConfiguration
    static class SubscriptionPlanServiceImplConfiguration{
        @Bean
        public SubscriptionPlanService subscriptionPlanService(){return new SubscriptionPlanServiceImpl();}
    }

    @Test
    @DisplayName("when GetSubscriptionPlansById With Valid Id Then Returns SubscriptionPlan")
    public void whenGetSubscriptionPlansByIdWithValidIdThenReturnsSubscriptionPlan(){
        //Arrange
        long id =1;
        SubscriptionPlan subscriptionPlan= new SubscriptionPlan();
        subscriptionPlan.setId(id);


        given(subscriptionPlanRepository.findById(subscriptionPlan.getId()))
                .willReturn(Optional.of(subscriptionPlan));

        //Act

        SubscriptionPlan foundSubscriptionPlan=subscriptionPlanService.getSubscriptionPlansById(id);
        //Assert
        assertThat(foundSubscriptionPlan.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetSubscriptionPlansById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetSubscriptionPlansByIdWithInvalidIdThenReturnsResourceNotFoundException(){


        //Arrange
        long id =2;
        String template = "Resource %s not found for %s with value %s";
        SubscriptionPlan subscriptionPlan= new SubscriptionPlan();
        when(subscriptionPlanRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "subscriptionPlan", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            SubscriptionPlan foundSubscriptionPlan=subscriptionPlanService.getSubscriptionPlansById(id);
        });
        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
