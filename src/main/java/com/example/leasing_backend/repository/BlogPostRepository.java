package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    // Custom query methods (if needed) can be added here
}
