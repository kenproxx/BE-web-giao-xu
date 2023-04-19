package com.example.airbnb.repository;

import com.example.airbnb.model.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query(value = "SELECT * FROM post_entity WHERE status = true ORDER BY created_date DESC LIMIT 1", nativeQuery = true)
    PostEntity getNewPost();

    @Query(value = "SELECT * FROM" +
            "    (SELECT ROW_NUMBER() over () as rn, content, title, status, created_date" +
            "     FROM post_entity where status = true " +
            "                      and created_date != (SELECT MAX(created_date) FROM post_entity WHERE status = true)" +
            "                      order by created_date desc ) as subquery" +
            "WHERE  rn between :pageBegin and :pageEnd", nativeQuery = true)
    Iterable<PostEntity> getListPostEnable(int pageBegin, int pageEnd);
}