package com.opense.traininggain.service;

import com.opense.traininggain.domain.model.Tag;
import com.opense.traininggain.domain.repository.TagRepository;
import com.opense.traininggain.domain.service.TagService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Page<Tag> getAllTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Tag getTagsById(Long tagId) {
       return tagRepository.findById(tagId).orElseThrow(()->new ResourceNotFoundException("Tag","Id",tagId));
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Long tagId, Tag tagDetails) {
        return tagRepository.findById(tagId).map(tag -> {
            tag.setName(tagDetails.getName());
            tag.setDescription(tagDetails.getDescription());
            return tagRepository.save(tag);
        }).orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
    }
    @Override
    public ResponseEntity<?> deleteTag(Long tagId) {
        return tagRepository.findById(tagId).map(tag -> {
            tagRepository.delete(tag);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
    }

}
