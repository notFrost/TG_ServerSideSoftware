package com.opense.traininggain.domain.service;

import com.opense.traininggain.domain.model.Equipament;
import com.opense.traininggain.domain.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TagService {
    Page<Tag> getAllTags(Pageable pageable);
    Tag getTagsById(Long tagId);
    Tag createTag(Tag tag);
    Tag updateTag(Long tagId, Tag tagDetails);
    ResponseEntity<?> deleteTag(Long tagId);
}
