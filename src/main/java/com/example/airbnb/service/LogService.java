package com.example.airbnb.service;

import com.example.airbnb.model.LogEntity;
import com.example.airbnb.model.PostEntity;
import com.example.airbnb.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void writeLog(String createdBy, String value) {
        Date now = new Date();
        LogEntity logEntity = new LogEntity(value, now, createdBy);
        logRepository.save(logEntity);
    }

    public List getAllLog(Integer page, Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_all_log", LogEntity.class);
        query.registerStoredProcedureParameter("page", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pageSize", Integer.class, ParameterMode.IN);
        query.setParameter("page", page);
        query.setParameter("pageSize", pageSize);
        query.execute();
        return query.getResultList();
    }}
