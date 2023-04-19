package com.example.airbnb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class GroupType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    private Long hashTagId;
}
