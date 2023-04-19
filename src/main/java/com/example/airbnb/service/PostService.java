package com.example.airbnb.service;

import com.example.airbnb.dto.PostDto;
import com.example.airbnb.model.PostEntity;
import com.example.airbnb.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Iterable<PostEntity> findAll(int page) {
        int pageSize = 6;
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return postRepository.findAll(pageRequest);
    }

    public void createPost(PostDto postDto) {
        PostEntity postEntity = new PostEntity();
        postEntity.setContent(postDto.getContent());
        postEntity.setTitle(postDto.getTitle());
        postEntity.setCreatedBy(postDto.getCreatedBy());
        postEntity.setStatus(postDto.isStatus());
        postEntity.setCreatedDate(new Date());
        postRepository.save(postEntity);
    }


}
