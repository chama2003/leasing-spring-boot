package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.BlogPostDto;
import com.example.leasing_backend.entity.BlogPost;
import com.example.leasing_backend.exception.ResourceNotFoundException;
import com.example.leasing_backend.repository.BlogPostRepository;
import com.example.leasing_backend.service.BlogPostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public BlogPostDto createBlogPost(BlogPostDto blogPostDto) {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(blogPostDto.getTitle());
        blogPost.setDescription(blogPostDto.getDescription());
        blogPost.setImage(blogPostDto.getImage());

        BlogPost savedBlogPost = blogPostRepository.save(blogPost);
        return new BlogPostDto(savedBlogPost);
    }

    @Override
    public BlogPostDto getBlogPostById(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog post not found with id " + id));
        return new BlogPostDto(blogPost);
    }

    @Override
    public List<BlogPostDto> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostRepository.findAll();
        return blogPosts.stream()
                .map(BlogPostDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public BlogPostDto updateBlogPostById(Long id, BlogPostDto updatedBlogPost) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog post not found with id " + id));

        blogPost.setTitle(updatedBlogPost.getTitle());
        blogPost.setDescription(updatedBlogPost.getDescription());
        blogPost.setImage(updatedBlogPost.getImage());

        BlogPost savedBlogPost = blogPostRepository.save(blogPost);
        return new BlogPostDto(savedBlogPost);
    }

    @Override
    public void deleteBlogPostById(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog post not found with id " + id));
        blogPostRepository.deleteById(id);
    }
}
