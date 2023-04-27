package com.example.airbnb.service;

import com.example.airbnb.model.LogEntity;
import com.example.airbnb.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void writeLog(String createdBy, String value) {
        LocalDateTime now = LocalDateTime.now();
        LogEntity logEntity = new LogEntity(value, now, createdBy);
        logRepository.save(logEntity);
    }

    public Page<LogEntity> getAllLog(Integer page, Integer pageSize) {
        if (page == null) {
            page = 0;
        } else {
            page--;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by("createdDate").descending());
        return logRepository.findAll(pageRequest);
    }
}
