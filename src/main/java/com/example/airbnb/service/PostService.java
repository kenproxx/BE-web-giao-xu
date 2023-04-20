package com.example.airbnb.service;

import com.example.airbnb.dto.PostDto;
import com.example.airbnb.model.PostEntity;
import com.example.airbnb.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private GroupTypeService groupTypeService;

    @Autowired
    private LogService logService;

    @PersistenceContext
    private EntityManager entityManager;
    public List findAll(int page) {
        int pageSize = 6;

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("find_post_paging", PostEntity.class);
        query.registerStoredProcedureParameter("page", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pageSize", Integer.class, ParameterMode.IN);
        query.setParameter("page", page);
        query.setParameter("pageSize", pageSize);
        query.execute();
        return query.getResultList();
    }



    public void createPost(PostDto postDto) {
        PostEntity postEntity = new PostEntity();
        String content = postDto.getContent();
        String title = postDto.getTitle();
        String createdBy = postDto.getCreatedBy();
        boolean status = postDto.isStatus();
        Date now = new Date();
        postEntity.setContent(content);
        postEntity.setTitle(title);
        postEntity.setCreatedBy(createdBy);
        postEntity.setStatus(status);
        postEntity.setCreatedDate(now);
        postRepository.save(postEntity);

        String value = createdBy + " đã tạo mới bài viết: " + title;
        logService.writeLog(createdBy, value);
    }

    public PostEntity getNewPost() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_newest_post", PostEntity.class);
        query.execute();
        return (PostEntity) query.getSingleResult();
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

    public void changeStatusPost(Long id, String username) {
        PostEntity postEntity = postRepository.getOne(id);
        boolean newStatus = !postEntity.isStatus();
        Date now = new Date();
        postEntity.setStatus(newStatus);
        postEntity.setUpdatedDate(now);
        postEntity.setUpdatedBy(username);
        postRepository.save(postEntity);
    }

    public void editPost(PostEntity postEntity) {
        Date now = new Date();
        postEntity.setUpdatedDate(now);
        postRepository.save(postEntity);
    }

    public PostEntity getByIdOfUser(Long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_post_by_id_of_user", PostEntity.class);
        query.registerStoredProcedureParameter("id", Long.class, ParameterMode.IN);
        query.setParameter("id", id);
        query.execute();
        return (PostEntity) query.getSingleResult();
    }
    public PostEntity getByIdOfAdmin(Long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_post_by_id_of_admin", PostEntity.class);
        query.registerStoredProcedureParameter("id", Long.class, ParameterMode.IN);
        query.setParameter("id", id);
        query.execute();
        return (PostEntity) query.getSingleResult();
    }


}
