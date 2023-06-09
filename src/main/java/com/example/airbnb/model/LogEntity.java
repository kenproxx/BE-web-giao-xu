package com.example.airbnb.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
public class LogEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    public LogEntity(String value, LocalDateTime now, String createdBy) {
        this.value = value;
        this.createdDate = now;
        this.createdBy = createdBy;
    }

    public LogEntity() {
    }
}
