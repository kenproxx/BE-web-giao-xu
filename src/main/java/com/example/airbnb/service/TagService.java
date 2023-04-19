package com.example.airbnb.service;

import com.example.airbnb.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;


}
