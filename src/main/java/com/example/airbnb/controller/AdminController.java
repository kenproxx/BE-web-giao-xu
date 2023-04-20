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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(PostDto postDto) {
        postService.createPost(postDto);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<PostEntity> getById(@RequestParam Long id) {
        PostEntity postEntity = postService.getByIdOfAdmin(id);
        if (postEntity == null) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(postEntity, HttpStatus.OK);
    }
}
