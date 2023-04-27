package com.example.airbnb.controller;

import com.example.airbnb.model.PostEntity;
import com.example.airbnb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List> findAll(@RequestParam(required = false) Integer page,
                                        @RequestParam(required = false) Integer pageSize) {
        if (pageSize == null) {
            pageSize = 6;
        }
        List listPost = postService.getListPost(page, pageSize, false);
        return new ResponseEntity<>(listPost, HttpStatus.OK);
    }

    @GetMapping("/newest")
    public ResponseEntity<PostEntity> getNewPost() {
        PostEntity postEntity = postService.getNewPost();
        return new ResponseEntity<>(postEntity, HttpStatus.OK);
    }

    @GetMapping("/find-by-tag")
    public ResponseEntity<List<PostEntity>> getListPostByTagId(@RequestParam Long tagId, @RequestParam(required = false) Integer page) {
        List listPost = postService.getListPostByTagId(tagId, page);
        return new ResponseEntity<>(listPost, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<PostEntity> getById(@RequestParam Long id) {
        PostEntity postEntity = postService.getById(id, false);
        if (postEntity == null) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(postEntity, HttpStatus.OK);
    }
}
