package com.lospollos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lospollos.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByFirstname(String firstname);
    boolean existsByFirstname(String firstname);
}
