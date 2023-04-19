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

    @GetMapping("/all")
    public ResponseEntity<Iterable<PostEntity>> findAll(@RequestParam(required = false) Integer page) {
        if (page == null) {
            page = 0;
        }
        Iterable<PostEntity> listPost = postService.findAll(page);
        return new ResponseEntity<>(listPost, HttpStatus.OK);
    }

    @GetMapping("/newest")
    public ResponseEntity<PostEntity> getNewPost() {
        PostEntity postEntity = postService.getNewPost();
        return new ResponseEntity<>(postEntity, HttpStatus.OK);
    }

    @GetMapping("/find-by-tag")
    public ResponseEntity<Iterable<PostEntity>> getListPostByTagId(@RequestParam Long tagId) {
        Iterable<PostEntity> listPost = postService.getListPostByTagId(tagId);
        return new ResponseEntity<>(listPost, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }
}
