package com.example.airbnb.controller;

import com.example.airbnb.model.TagEntity;
import com.example.airbnb.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/add")
    public ResponseEntity<TagEntity> createTag(@RequestBody TagEntity tagEntity) {
        return tagService.createNewTag(tagEntity);
    }
}
