package com.opense.traininggain;

import com.opense.traininggain.domain.model.Specialist;
import com.opense.traininggain.domain.model.User;
import com.opense.traininggain.domain.repository.SpecialistRepository;
import com.opense.traininggain.domain.repository.UserRepository;
import com.opense.traininggain.domain.service.SpecialistService;
import com.opense.traininggain.domain.service.UserService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import com.opense.traininggain.service.SpecialistServiceImpl;
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

    public class SpecialistServiceImplIntegrationTest {

        @MockBean
        private SpecialistRepository specialistRepository;
        @MockBean
        private UserRepository userRepository;

        @Autowired
        private SpecialistService specialistService;
        @Autowired
        private UserService userService;


        @TestConfiguration
        static class SpecialistServiceImplTestConfiguration {
            @Bean
            public SpecialistService specialistService(){return new SpecialistServiceImpl();}
            @Bean
            public UserService userService() {  return new UserServiceImpl();}





        }
        @Test
        @DisplayName("when GetSpecialistById With Valid Id Then Returns Specialist")
        public void whenGetSpecislistByIdWithValidIdThenReturnsSpecialist() {

            //Arrange
            long id =1;
            Specialist specialist= new Specialist();
            specialist.setId(id);
            //given(postRepository.findByTitle(post.getTitle()))
            //    .willReturn(Optional.of(post));
            when(specialistRepository.findById(id))
                    .thenReturn(Optional.of(specialist));
            //Act

            Specialist foundSpecialist = specialistService.getSpecialistById(id);
            //Assert
            assertThat(foundSpecialist.getId()).isEqualTo(id);

        }

        @Test
        @DisplayName("when GetSpecialistById With Invalid Id Then Returns Resource Not Found Exception")
        public void whenGetSpecialistByIdWithInvalidIdThenReturnsResourceNotFoundException() {

//Arrange
            long id =2;
            String template = "Resource %s not found for %s with value %s";
            Specialist specialist= new Specialist();
            when(specialistRepository.findById(id))
                    .thenReturn(Optional.empty());
            String expectedMessage = String.format(template, "Specialist", "Id", id);

            // Act
            Throwable exception = catchThrowable(() -> {
                Specialist foundSpecialist= specialistService.getSpecialistById(id);
            });
            // Assert
            assertThat(exception)
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessage(expectedMessage);

        }



    }

