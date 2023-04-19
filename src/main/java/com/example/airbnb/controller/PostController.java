package com.example.airbnb.controller;

import com.example.airbnb.dto.PostDto;
import com.example.airbnb.model.PostEntity;
import com.example.airbnb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;



    @GetMapping
    public ResponseEntity<Iterable<PostEntity>> findAll(@RequestParam int page) {
        Iterable<PostEntity> listPost = postService.findAll(page);
        return new ResponseEntity<>(listPost, HttpStatus.OK);
    }
}
