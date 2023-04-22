package com.example.airbnb.service;

import com.example.airbnb.model.LogEntity;
import com.example.airbnb.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public void writeLog(String createdBy, String value) {
        Date now = new Date();
        LogEntity logEntity = new LogEntity(value, now, createdBy);
        logRepository.save(logEntity);
    }

    public List getAllLog(Integer page) {
        return logRepository.findAll();
    }
}
