package com.example.airbnb.repository;

import com.example.airbnb.model.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query(value = "SELECT * FROM post_entity WHERE status = true ORDER BY created_date DESC LIMIT 1", nativeQuery = true)
    PostEntity getNewPost();
}