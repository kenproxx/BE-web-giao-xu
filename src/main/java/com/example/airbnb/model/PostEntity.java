package com.example.airbnb.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
@Data
@Entity
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private boolean status;

    private String thumbnailImg;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

}
