package com.scheduleproject.scheduleproject.repository;

import com.scheduleproject.scheduleproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}