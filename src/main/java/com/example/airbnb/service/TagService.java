package com.example.airbnb.service;

import com.example.airbnb.model.TagEntity;
import com.example.airbnb.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class TagService {
    @Autowired
    private LogService logService;

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

        Date now = new Date();
        tagEntity.setCreatedDate(now);
        String tagName = tagEntity.getKeyName();
        String createdBy = tagEntity.getCreatedBy();
        String value = createdBy + " đã tạo mới Tag: " + tagName;

        tagRepository.save(tagEntity);
        logService.writeLog(createdBy, value);

        return new ResponseEntity<>(tagEntity, HttpStatus.OK);
    }

    public ResponseEntity<TagEntity> editTag(TagEntity tagEntity) {
        List<String> listKeyTag = listKeyTag();
        String keyName = tagEntity.getKeyName();

        boolean isKeyTag = listKeyTag.stream().anyMatch(key -> key.equals(keyName));
        if (isKeyTag) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Date now = new Date();
        tagEntity.setUpdatedDate(now);
        String createdBy = tagEntity.getCreatedBy();
        String value = createdBy + " đã sửa Tag: ";

        tagRepository.save(tagEntity);
        logService.writeLog(createdBy, value);

        return new ResponseEntity<>(tagEntity, HttpStatus.OK);
    }
}
