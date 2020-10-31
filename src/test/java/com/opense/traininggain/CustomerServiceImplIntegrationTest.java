package com.opense.traininggain;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.model.User;
import com.opense.traininggain.domain.repository.CustomerRepository;
import com.opense.traininggain.domain.repository.UserRepository;
import com.opense.traininggain.domain.service.CustomerService;
import com.opense.traininggain.domain.service.UserService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import com.opense.traininggain.service.CustomerServiceImpl;
import com.opense.traininggain.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CustomerServiceImplIntegrationTest {
    @MockBean
    private CustomerRepository customerRepository;


    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @TestConfiguration
    static class CustomerServiceImplTestConfiguration{

        @Bean
        public CustomerService customerService(){return new CustomerServiceImpl(); }
        @Bean
        public UserService userService(){return new UserServiceImpl();}
    }


    @Test
    @DisplayName("when GetCustomerById With Valid Id Then Returns Customer")
    public void whenGetCustomerByIdWithValidIdThenReturnsException(){
        //Arrange
        long id =1;
        Customer customer= new Customer();
        customer.setId(id);


        //given(postRepository.findByTitle(post.getTitle()))
        //        .willReturn(Optional.of(post));
        when(customerRepository.findById(id))
                .thenReturn(Optional.of(customer));
        //Act

        Customer foundcustomer=customerService.getCustomerById(id);
        //Assert
        assertThat(foundcustomer.getId()).isEqualTo(id);
    }
    @Test
    @DisplayName("when GetCustomerById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetCustomerByIdWithInvalidIdThenReturnsResourceNotFoundException(){


        //Arrange
        long id =2;
        String template = "Resource %s not found for %s with value %s";
        Customer customer= new Customer();
        when(customerRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Customer", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Customer foundCustomer = customerService.getCustomerById(id);
        });
        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}