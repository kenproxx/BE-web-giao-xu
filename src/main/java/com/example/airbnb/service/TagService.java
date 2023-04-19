package com.example.airbnb.service;

import com.example.airbnb.model.TagEntity;
import com.example.airbnb.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<String> listKeyTag() {
        LinkedList<String> listKeyTag = new LinkedList<>();
        tagRepository.findAll().forEach(tag -> listKeyTag.add(tag.getKeyName()));
        return listKeyTag;
    }

    public ResponseEntity<TagEntity> createNewTag(TagEntity tagEntity) {
        List<String> listKeyTag = listKeyTag();
        String keyName = tagEntity.getKeyName();
        boolean isKeyTag = listKeyTag.stream().anyMatch(key -> key.equals(keyName));
        if (isKeyTag) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        tagRepository.save(tagEntity);
        return new ResponseEntity<>(tagEntity, HttpStatus.OK);
    }
}
