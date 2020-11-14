package com.opense.traininggain.domain.repository;

import com.opense.traininggain.domain.model.Session;
import com.opense.traininggain.domain.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface TagRepository extends JpaRepository<Tag,Long> {

}
