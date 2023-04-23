package com.example.airbnb.controller;

import com.example.airbnb.dto.PostDto;
import com.example.airbnb.model.PostEntity;
import com.example.airbnb.security.jwt.JwtAuthenticationFilter;
import com.example.airbnb.service.LogService;
import com.example.airbnb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/all")
    public ResponseEntity<List> getAllLog(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        List list = logService.getAllLog(page, pageSize);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
