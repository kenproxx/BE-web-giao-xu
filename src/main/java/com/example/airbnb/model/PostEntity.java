package com.example.airbnb.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
@Data
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private ArrayList<HashTag> hashTagList;
    private boolean status;
    private String thumbnailImg;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;

}
