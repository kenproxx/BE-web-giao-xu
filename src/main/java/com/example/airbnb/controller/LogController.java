package com.example.airbnb.controller;

import com.example.airbnb.model.LogEntity;
import com.example.airbnb.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/all")
    public ResponseEntity<Page<LogEntity>> getAllLog(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        Page<LogEntity> list = logService.getAllLog(page, pageSize);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
