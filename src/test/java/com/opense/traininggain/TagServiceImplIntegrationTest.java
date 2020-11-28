package com.opense.traininggain;

import com.opense.traininggain.domain.model.Equipament;
import com.opense.traininggain.domain.model.Session;
import com.opense.traininggain.domain.model.Specialist;
import com.opense.traininggain.domain.model.Tag;
import com.opense.traininggain.domain.repository.TagRepository;
import com.opense.traininggain.domain.service.SessionService;
import com.opense.traininggain.domain.service.TagService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import com.opense.traininggain.service.SessionServiceImpl;
import com.opense.traininggain.service.TagServiceImpl;
import io.cucumber.java.bs.A;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TagServiceImplIntegrationTest {
    @MockBean
    private TagRepository tagRepository;
    @Autowired
    private TagService tagService;

    @TestConfiguration
    static class TagServiceImplTestConfiguration{
        @Bean
        public TagService tagService(){return new TagServiceImpl();}
    }
    ///
    @Test
    @DisplayName("when GetTagById With Valid Id Then Returns Tag")
    public void whenGetTagByIdWithValidIdThenReturnsTag(){
        //Arrange
        long id =1;
        Tag tag= new Tag();
        tag.setId(id);


        given(tagRepository.findById(tag.getId()))
              .willReturn(Optional.of(tag));

        //Act

        Tag foundTag=tagService.getTagsById(id);
        //Assert
        assertThat(foundTag.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetTagById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetTagByIdWithInvalidIdThenReturnsResourceNotFoundException(){


        //Arrange
        long id =2;
        String template = "Resource %s not found for %s with value %s";
        Tag tag= new Tag();
        when(tagRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Tag", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Tag foundTag=tagService.getTagsById(id);
        });
        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
