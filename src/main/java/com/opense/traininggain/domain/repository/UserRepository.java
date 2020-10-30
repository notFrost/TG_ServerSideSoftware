package com.opense.traininggain.domain.repository;

import com.opense.traininggain.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
