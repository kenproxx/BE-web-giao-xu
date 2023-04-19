package com.example.airbnb.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String keyName;

    private String value;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;
}
