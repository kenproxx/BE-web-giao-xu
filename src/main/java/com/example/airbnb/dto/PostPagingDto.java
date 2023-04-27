package com.example.airbnb.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PostPagingDto {
    private Long id;

    private String title;

    private String content;

    private Boolean status;

    private String thumbnail_img;

    private String created_by;

    private LocalDateTime created_date;

    private String updated_by;

    private LocalDateTime updated_date;

    private Integer total_item;

    private Integer total_page;


}
