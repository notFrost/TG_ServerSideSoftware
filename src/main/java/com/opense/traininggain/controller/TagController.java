package com.opense.traininggain.controller;

import com.opense.traininggain.domain.model.Tag;
import com.opense.traininggain.domain.service.TagService;
import com.opense.traininggain.resource.SaveTagResource;
import com.opense.traininggain.resource.TagResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get All Tags", description = "Get All available Tags", tags = {"Tag"})
    @GetMapping("/tags")
    public Page<TagResource> getAllTags(Pageable pageable) {
        List<TagResource> tags = tagService.getAllTags(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int tagCount = tags.size();
        return new PageImpl<>(tags, pageable, tagCount);

    }

    @Operation(summary = "Get Tag By Id", description = "Get Tag for given Id", tags = {"Tag"})
    @GetMapping("/tags/{tagId}")
    public TagResource getTagById(
            @PathVariable(name = "tagId") Long tagId) {
        return convertToResource(tagService.getTagsById(tagId));
    }

    @Operation(summary = "Create Tag", description = "Create a new Tag", tags = {"Tag"})
    @PostMapping("/tags")
    public TagResource createTag(@Valid @RequestBody SaveTagResource resource) {
        return convertToResource(tagService.createTag(
                convertToEntity(resource)));
    }

    @Operation(summary = "Update Tag", description = "Update Tag with given Id", tags = {"Tag"})
    @PutMapping("/tags/{tagId}")
    public TagResource updateTag(
            @PathVariable(name = "tagId") Long tagId,
            @Valid @RequestBody SaveTagResource resource) {
        return convertToResource(tagService.updateTag(tagId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete Tag", description = "Delete Tag with given Id", tags = {"Tag"})
    @DeleteMapping("/tags/{tagId}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "tagId") Long tagId) {
        return tagService.deleteTag(tagId);
    }

    private Tag convertToEntity(SaveTagResource resource) {
        return mapper.map(resource, Tag.class);
    }

    private TagResource convertToResource(Tag entity) {
        return mapper.map(entity, TagResource.class);
    }

}
