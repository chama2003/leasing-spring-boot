package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.BlogPostDto;
import java.util.List;

public interface BlogPostService {

    BlogPostDto createBlogPost(BlogPostDto blogPostDto);

    BlogPostDto getBlogPostById(Long id);

    List<BlogPostDto> getAllBlogPosts();

    BlogPostDto updateBlogPostById(Long id, BlogPostDto updatedBlogPost);

    void deleteBlogPostById(Long id);
}
