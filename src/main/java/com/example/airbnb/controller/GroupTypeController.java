package com.example.airbnb.controller;

import com.example.airbnb.service.GroupTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/type")
public class GroupTypeController {
    @Autowired
    private GroupTypeService groupTypeService;

    @GetMapping
    public List getCountByTagId() {
        return groupTypeService.getCountByTagId();
    }
}
