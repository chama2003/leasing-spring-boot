package com.example.leasing_backend.dto;

import com.example.leasing_backend.entity.BlogPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDto {

    private Long id;
    private String title;
    private String description;
    private String image;

    // Constructor to convert from BlogPost entity
    public BlogPostDto(BlogPost blogPost) {
        this.id = blogPost.getId();
        this.title = blogPost.getTitle();
        this.description = blogPost.getDescription();
        this.image = blogPost.getImage();
    }
}
