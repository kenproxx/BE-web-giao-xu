package com.example.airbnb.service;

import com.example.airbnb.dto.PostDto;
import com.example.airbnb.model.PostEntity;
import com.example.airbnb.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private GroupTypeService groupTypeService;

    public Iterable<PostEntity> findAll(int page) {
        int pageSize = 6;
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return postRepository.findAll(pageRequest);
    }

    public void createPost(PostDto postDto) {
        PostEntity postEntity = new PostEntity();
        System.out.println(postDto);
        postEntity.setContent(postDto.getContent());
        postEntity.setTitle(postDto.getTitle());
        postEntity.setCreatedBy(postDto.getCreatedBy());
        postEntity.setStatus(postDto.isStatus());
        postEntity.setCreatedDate(new Date());
        postRepository.save(postEntity);
    }

    public PostEntity getNewPost() {
        return postRepository.getNewPost();
    }

    public Iterable<PostEntity> getListPostByTagId(Long tagId) {
        LinkedList<Long> lists = groupTypeService.getListPostIdByTagId(tagId);
        LinkedList<PostEntity> listPost = new LinkedList<>();
        lists.forEach(item -> {
            PostEntity postEntity = postRepository.getOne(item);
            listPost.add(postEntity);
        });
        return listPost;
    }
    


}
