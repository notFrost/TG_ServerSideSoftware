package com.opense.traininggain;

import com.opense.traininggain.domain.model.Equipament;
import com.opense.traininggain.domain.model.Session;
import com.opense.traininggain.domain.model.Specialist;
import com.opense.traininggain.domain.repository.*;
import com.opense.traininggain.domain.service.EquipamentService;
import com.opense.traininggain.domain.service.SessionService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import com.opense.traininggain.service.EquipamentServiceImpl;
import com.opense.traininggain.service.SessionServiceImpl;
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
public class SessionServiceImplIntegrationTest {
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private TagRepository tagRepository;
    @MockBean
    private SpecialistRepository specialistRepository;
    @MockBean
    private SessionRepository sessionRepository;
    @MockBean
    private EquipamentRepository equipamentRepository;
    @Autowired
    private SessionService sessionService;

    @TestConfiguration
    static class SessionServiceImplTestConfiguration{
        @Bean
        public SessionService sessionService(){return new SessionServiceImpl();}
    }
    @Test
    @DisplayName("when getSessionByIdAndSpecialistId With Valid Id And SpecialistId Then Returns Session")
    public void whengetSessionByIdAndSpecialistIdWithValidIdAndSpecialistIdThenReturnsSession(){
        //Arrange
        long id =1;
        Specialist specialist=new Specialist();
        specialist.setId(id);
        Session session= new Session();
        session.setSpecialist(specialist);
        session.setId(id);


        //given(postRepository.findByTitle(post.getTitle()))
        //        .willReturn(Optional.of(post));
        when(sessionRepository.findByIdAndSpecialistId(id,id))
                .thenReturn(Optional.of(session));
        //Act

        Session foundSession=sessionService.getSessionByIdAndSpecialistId(id,id);
        //Assert
        assertThat(foundSession.getId()).isEqualTo(id);
    }
    @Test
    @DisplayName("when GetCustomerById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetEquipamentByIdWithInvalidIdThenReturnsResourceNotFoundException(){

        //Arrange
        long id =2;
        String template = "Session not found with %s %s and %s %s";
        Specialist specialist=new Specialist();
        Session session= new Session();
        when(equipamentRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Id", id, "SpecialistId",id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Session foundSession=sessionService.getSessionByIdAndSpecialistId(id,id);
        });
        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
