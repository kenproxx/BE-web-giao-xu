package com.example.airbnb.repository;

import com.example.airbnb.model.GroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupTypeRepository extends JpaRepository<GroupType, Long> {

    List<GroupType> findAllByHashTagId(Long hashTagId);

    @Query(value = "select key_name, hash_tag_id, count(*) " +
            "from group_type join tag_entity " +
            "on hash_tag_id = tag_entity.id " +
            "group by hash_tag_id", nativeQuery = true)
    List<Object> getCountByTagId();
}
