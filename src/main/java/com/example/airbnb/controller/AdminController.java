package com.example.airbnb.controller;

import com.example.airbnb.dto.PostDto;
import com.example.airbnb.model.PostEntity;
import com.example.airbnb.security.jwt.JwtAuthenticationFilter;
import com.example.airbnb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PostService postService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, HttpServletRequest request) {
        String username = jwtAuthenticationFilter.getUsernameFromJwt(request);
        postService.createPost(postDto, username);
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

    @PutMapping("/change-status")
    public ResponseEntity<PostEntity> changeStatus(@RequestParam Long id, HttpServletRequest request) {
        String username = jwtAuthenticationFilter.getUsernameFromJwt(request);
        postService.changeStatusPost(id, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit-post")
    public ResponseEntity<PostEntity> editPost(@RequestBody PostEntity postEntity, HttpServletRequest request) {
        String username = jwtAuthenticationFilter.getUsernameFromJwt(request);
        postService.editPost(postEntity, username);
        return new ResponseEntity<>(postEntity, HttpStatus.OK);
    }
}
