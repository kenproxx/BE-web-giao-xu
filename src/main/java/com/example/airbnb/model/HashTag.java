package com.example.airbnb.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String key;
    private String value;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
}
