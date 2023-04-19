package com.example.airbnb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String keyName;

    private String value;
}
