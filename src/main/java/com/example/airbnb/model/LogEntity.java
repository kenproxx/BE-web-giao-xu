package com.example.airbnb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    private Date createdDate;

    private String createdBy;

    public LogEntity(String value, Date now, String createdBy) {
        this.value = value;
        this.createdDate = now;
        this.createdBy = createdBy;
    }

    public LogEntity() {
    }
}
