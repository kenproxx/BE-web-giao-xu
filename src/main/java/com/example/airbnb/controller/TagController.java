package com.example.airbnb.controller;

import com.example.airbnb.model.TagEntity;
import com.example.airbnb.model.User;
import com.example.airbnb.security.jwt.JwtAuthenticationFilter;
import com.example.airbnb.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @PostMapping("/add")
    public ResponseEntity<TagEntity> createTag(@RequestBody TagEntity tagEntity, HttpServletRequest request) {
        String username = jwtAuthenticationFilter.getUsernameFromJwt(request);
        return tagService.createNewTag(tagEntity, username);
    }

    @PostMapping("/edit")
    public ResponseEntity<TagEntity> editTag(@RequestBody TagEntity tagEntity, HttpServletRequest request) {
        String username = jwtAuthenticationFilter.getUsernameFromJwt(request);
        return tagService.editTag(tagEntity, username);
    }


    @GetMapping("/all")
    public ResponseEntity<Iterable<TagEntity>> getAllTag() {
        Iterable<TagEntity> users = tagService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
