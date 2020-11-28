package com.opense.traininggain;


import com.opense.traininggain.domain.model.User;
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

public class UserServiceImplIntegrationTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @TestConfiguration
    static class UserServiceImplTestConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }
@Test
@DisplayName("when GetUserById With Valid Id Then Returns User")
    public void whenGetUserByIdWithValidIdThenReturnsUser() {

    //Arrange
    long id =1;
    User user= new User();
    user.setId(id);
    //given(postRepository.findByTitle(post.getTitle()))
    //    .willReturn(Optional.of(post));
    when(userRepository.findById(id))
            .thenReturn(Optional.of(user));
    //Act

    User foundUser = userService.getUserById(id);
    //Assert
    assertThat(foundUser.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetUserById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetUserByIdWithInvalidIdThenReturnsResourceNotFoundException() {

        //Arrange
        long id =2;
        String template = "Resource %s not found for %s with value %s";
        User user= new User();
        when(userRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "User", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            User foundUser = userService.getUserById(id);
        });
        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

}
