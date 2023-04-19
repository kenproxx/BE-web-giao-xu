package com.example.airbnb.repository;

import com.example.airbnb.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query(value = "SELECT * FROM post_entity ORDER BY created_date DESC LIMIT 1", nativeQuery = true)
    PostEntity getNewPost();


}