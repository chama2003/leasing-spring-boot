package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.BlogPostDto;
import com.example.leasing_backend.service.BlogPostService;
import com.example.leasing_backend.service.impl.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/blog-posts")
@CrossOrigin("*")
public class BlogPostController {

    private final BlogPostService blogPostService;
    private final StorageService storageService;

    public BlogPostController(BlogPostService blogPostService, StorageService storageService) {
        this.blogPostService = blogPostService;
        this.storageService = storageService;
    }

    // Get all blog posts
    @GetMapping
    public ResponseEntity<List<BlogPostDto>> getAllBlogPosts() {
        List<BlogPostDto> blogPostDtos = blogPostService.getAllBlogPosts();
        return ResponseEntity.ok(blogPostDtos);
    }

    // Add a new blog post
    @PostMapping
    public ResponseEntity<BlogPostDto> addBlogPost(
            @RequestParam("image") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description) throws IOException {

        String fileName = storageService.uploadImage(file); // Upload image using StorageService

        BlogPostDto blogPostDto = new BlogPostDto();
        blogPostDto.setTitle(title);
        blogPostDto.setDescription(description);
        blogPostDto.setImage(fileName); // Save the image file name (path) in the database

        BlogPostDto savedBlogPost = blogPostService.createBlogPost(blogPostDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBlogPost);
    }

    // Edit a blog post by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> editBlogPost(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description) throws IOException {

        // Check if the blog post exists before editing
        BlogPostDto blogPostDto = blogPostService.getBlogPostById(id);

        if (blogPostDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog post not found with id: " + id);
        }

        String fileName = storageService.uploadImage(file); // Upload new image
        blogPostDto.setTitle(title);
        blogPostDto.setDescription(description);
        blogPostDto.setImage(fileName); // Update image path

        BlogPostDto updatedBlogPost = blogPostService.updateBlogPostById(id, blogPostDto);

        return ResponseEntity.ok(updatedBlogPost);
    }

    // Delete a blog post by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPostById(id);
        return ResponseEntity.ok("Blog post deleted successfully");
    }

    // Download an image by its filename
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }
}
