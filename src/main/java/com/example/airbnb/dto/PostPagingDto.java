package com.example.airbnb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDateTime;


@SqlResultSetMapping(
        name = "postInfoMapping",
        classes = @ConstructorResult(
                targetClass = PostPagingDto.class,
                columns = {
                @ColumnResult(name = "id", type = Long.class),
                @ColumnResult(name = "title", type = String.class),
                @ColumnResult(name = "content", type = String.class),
                @ColumnResult(name = "status", type = Boolean.class),
                @ColumnResult(name = "thumbnail_img", type = String.class),
                @ColumnResult(name = "created_by", type = String.class),
                @ColumnResult(name = "created_date", type = LocalDateTime.class),
                @ColumnResult(name = "updated_by", type = String.class),
                @ColumnResult(name = "updated_date", type = LocalDateTime.class),
                @ColumnResult(name = "total_item", type = Integer.class),
                @ColumnResult(name = "total_page", type = Integer.class)
        })
)
@MappedSuperclass
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
