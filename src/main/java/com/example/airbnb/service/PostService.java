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
import java.time.LocalDateTime;
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

    public List getListPost(int page, boolean isAdmin) {
        int pageSize = 6;
        String procedure = isAdmin ? "find_post_paging_admin" : "find_post_paging";
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(procedure, PostEntity.class);
        query.registerStoredProcedureParameter("page", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pageSize", Integer.class, ParameterMode.IN);
        query.setParameter("page", page);
        query.setParameter("pageSize", pageSize);
        query.execute();
        return query.getResultList();
    }



    public void createPost(PostDto postDto, String createdBy) {
        PostEntity postEntity = new PostEntity();
        String content = postDto.getContent();
        String title = postDto.getTitle();
        String thumbnailImg = postDto.getThumbnailImg();
        boolean status = postDto.isStatus();
        LocalDateTime now = LocalDateTime.now();
        postEntity.setContent(content);
        postEntity.setTitle(title);
        postEntity.setCreatedBy(createdBy);
        postEntity.setThumbnailImg(thumbnailImg);
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

    public void changeStatusPost(Long id, String updatedBy) {
        PostEntity postEntity = postRepository.getOne(id);
        boolean newStatus = !postEntity.isStatus();
        LocalDateTime now = LocalDateTime.now();
        postEntity.setStatus(newStatus);
        postEntity.setUpdatedDate(now);
        postEntity.setUpdatedBy(updatedBy);
        postRepository.save(postEntity);

        String title = postEntity.getTitle();

        String value = updatedBy + " đã thay đổi trạng thái bài viết: " + title;
        logService.writeLog(updatedBy, value);
    }

    public void editPost(PostEntity postEntity, String updatedBy) {
        LocalDateTime now = LocalDateTime.now();
        postEntity.setUpdatedDate(now);
        postEntity.setUpdatedBy(updatedBy);
        postRepository.save(postEntity);

        String title = postEntity.getTitle();

        String value = updatedBy + " đã sửa bài viết: " + title;
        logService.writeLog(updatedBy, value);
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

    public List getListPostByTagId(Long id, Integer page) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_list_post_by_tag_id", PostEntity.class);
        int pageSize = 6;
        if (page == null) {
            page = 1;
        }
        query.registerStoredProcedureParameter("tagId", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("page", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pageSize", Integer.class, ParameterMode.IN);
        query.setParameter("tagId", id);
        query.setParameter("page", page);
        query.setParameter("pageSize", pageSize);
        query.execute();
        return query.getResultList();
    }


}
