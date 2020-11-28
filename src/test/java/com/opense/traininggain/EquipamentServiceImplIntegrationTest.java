package com.opense.traininggain;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.model.Equipament;
import com.opense.traininggain.domain.model.Session;
import com.opense.traininggain.domain.repository.EquipamentRepository;
import com.opense.traininggain.domain.repository.SessionRepository;
import com.opense.traininggain.domain.service.CustomerService;
import com.opense.traininggain.domain.service.EquipamentService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import com.opense.traininggain.service.CustomerServiceImpl;
import com.opense.traininggain.service.EquipamentServiceImpl;
import io.cucumber.java.pt.E;
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
public class EquipamentServiceImplIntegrationTest {
    @MockBean
    private SessionRepository sessionRepository;
    @MockBean
    private EquipamentRepository equipamentRepository;

    @Autowired
    private EquipamentService equipamentService;

    @TestConfiguration
    static class EquipamentServiceImplTestConfiguration{
        @Bean
        public EquipamentService equipamentService(){return new EquipamentServiceImpl();}
    }
    @Test
    @DisplayName("when GetEquipamentById With Valid Id Then Returns Equipament")
    public void whenGetEquipamentByIdWithValidIdThenReturnsEquipament(){
        //Arrange
        long id =1;
        Equipament equipament= new Equipament();
        equipament.setId(id);


        //given(postRepository.findByTitle(post.getTitle()))
        //        .willReturn(Optional.of(post));
        when(equipamentRepository.findById(id))
                .thenReturn(Optional.of(equipament));
        //Act

        Equipament foundEquipament=equipamentService.getEquipamentById(id);
        //Assert
        assertThat(foundEquipament.getId()).isEqualTo(id);
    }
    @Test
    @DisplayName("when GetCustomerById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetEquipamentByIdWithInvalidIdThenReturnsResourceNotFoundException(){


        //Arrange
        long id =2;
        String template = "Resource %s not found for %s with value %s";
        Session session= new Session();
        when(sessionRepository.findByIdAndSpecialistId(id,id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Equipament", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Equipament foundEquipament=equipamentService.getEquipamentById(id);
        });
        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }


}
