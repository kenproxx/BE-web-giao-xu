package com.example.airbnb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PostEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private boolean status;

    private String thumbnailImg;



}
