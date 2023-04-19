package com.example.airbnb.dto;

import lombok.Data;

@Data
public class PostDto {
    private String title;

    private String content;

    private String createdBy;

    private boolean status;

}
