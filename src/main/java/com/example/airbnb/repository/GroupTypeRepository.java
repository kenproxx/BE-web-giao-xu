package com.example.airbnb.repository;

import com.example.airbnb.model.GroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupTypeRepository extends JpaRepository<GroupType, Long> {

    List<GroupType> findAllByHashTagId(Long hashTagId);
}
