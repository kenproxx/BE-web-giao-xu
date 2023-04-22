package com.example.airbnb.service;

import com.example.airbnb.model.GroupType;
import com.example.airbnb.repository.GroupTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class GroupTypeService {
    @Autowired
    private GroupTypeRepository groupTypeRepository;


    public List<Object> getCountByTagId() {
       return groupTypeRepository.getCountByTagId();
    }


}
