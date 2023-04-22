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

    public List<TagEntity> getAll() {
        return tagRepository.findAll();
    }

    public ResponseEntity<TagEntity> createNewTag(TagEntity tagEntity, String createdBy) {

        if (isExistTag(tagEntity.getKeyName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Date now = new Date();
        tagEntity.setCreatedDate(now);
        String tagName = tagEntity.getKeyName();
        tagEntity.setCreatedBy(createdBy);
        String value = createdBy + " đã tạo mới Tag: " + tagName;

        tagRepository.save(tagEntity);
        logService.writeLog(createdBy, value);

        return new ResponseEntity<>(tagEntity, HttpStatus.OK);
    }

    public boolean isExistTag(String keyName) {
        return getAll().stream().anyMatch(key -> key.getKeyName().equals(keyName));

        //nếu đã tồn tại Tag ====> return True

    }

    public ResponseEntity<TagEntity> editTag(TagEntity tagEntity, String updatedBy) {


        if (isExistTag(tagEntity.getKeyName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Date now = new Date();
        tagEntity.setUpdatedDate(now);
        tagEntity.setUpdatedBy(updatedBy);
        String value = updatedBy + " đã sửa Tag: ";

        tagRepository.save(tagEntity);
        logService.writeLog(updatedBy, value);

        return new ResponseEntity<>(tagEntity, HttpStatus.OK);
    }
}
