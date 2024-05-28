package com.scheduleproject.scheduleproject.repository;

import com.scheduleproject.scheduleproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}