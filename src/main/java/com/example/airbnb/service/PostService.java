package com.example.airbnb.service;

import com.example.airbnb.dto.PostDto;
import com.example.airbnb.dto.PostPagingDto;
import com.example.airbnb.model.PostEntity;
import com.example.airbnb.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    private final JdbcTemplate jdbcTemplate;

    SimpleJdbcCall jdbcCall;

    public PostService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List getListPost(Integer page,Integer pageSize, boolean isAdmin) {
        if (page == null) {
            page = 1;
        }
        jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_post_info");

        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("page", page)
                .addValue("pageSize", pageSize)
                .addValue("isAdmin", isAdmin);

        Map<String, Object> outParams = jdbcCall.execute(inParams);

        List<PostPagingDto> postPagingDtoList = (List<PostPagingDto>) outParams.get("#result-set-1");

        return postPagingDtoList;
    }
    public Integer getCountPost(boolean isAdmin) {
        String sql = "{CALL get_post_count(?)}";
        Object[] params = { isAdmin ? 1 : 0 };
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
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
        LocalDateTime now = LocalDateTime.now();

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("change_status_post", PostEntity.class);
        query.registerStoredProcedureParameter("idPost", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("updateBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("updateDate", LocalDateTime.class, ParameterMode.IN);
        query.setParameter("idPost", id);
        query.setParameter("updateBy", updatedBy);
        query.setParameter("updateDate", now);
        query.execute();

        String value = updatedBy + " đã thay đổi trạng thái của bài viết: " + id;
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

    public PostEntity getById(Long id, boolean isAdmin) {
        String procedure = isAdmin ? "get_post_by_id_of_admin" : "get_post_by_id_of_user";
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(procedure, PostEntity.class);
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
