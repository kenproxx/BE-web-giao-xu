package com.example.airbnb.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseEntity {
    protected String createdBy;

    protected LocalDateTime createdDate;

    protected String updatedBy;

    protected LocalDateTime updatedDate;
}
