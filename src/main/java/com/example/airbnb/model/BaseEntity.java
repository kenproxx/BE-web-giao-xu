package com.example.airbnb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    protected String createdBy;

    protected LocalDateTime createdDate;

    protected String updatedBy;

    protected LocalDateTime updatedDate;
}
