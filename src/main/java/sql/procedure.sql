alter table post_entity
    modify content LONGTEXT null;

alter table post_entity
    modify thumbnail_img MEDIUMTEXT null;


INSERT INTO role (name)
VALUES ('ROLE_ADMIN');


delimiter //
create procedure find_post_paging(
    page int,
    pageSize int
)
begin
    SELECT id,
           content,
           created_by,
           created_date,
           status,
           thumbnail_img,
           title,
           updated_by,
           updated_date
    FROM (SELECT *, ROW_NUMBER() over () as rn
          FROM post_entity
          where status = true
            and created_date != (SELECT MAX(created_date) FROM post_entity WHERE status = true)
          order by created_date desc) as subquery
    WHERE rn between ((page - 1) * pageSize + 1) and (((page - 1) * pageSize) + pageSize);
end //
delimiter ;

delimiter //
create procedure find_post_paging_admin(
    page int,
    pageSize int
)
begin
    SELECT id,
           content,
           created_by,
           created_date,
           status,
           thumbnail_img,
           title,
           updated_by,
           updated_date
    FROM (SELECT *, ROW_NUMBER() over () as rn
          FROM post_entity
          where created_date != (SELECT MAX(created_date) FROM post_entity)
          order by created_date desc) as subquery
    WHERE rn between ((page - 1) * pageSize + 1) and (((page - 1) * pageSize) + pageSize);
end //
delimiter ;


delimiter //
create procedure get_newest_post()
begin
    SELECT * FROM post_entity WHERE status = true ORDER BY created_date DESC LIMIT 1;
end //
delimiter ;

delimiter //
create procedure get_all_log(
    page int,
    pageSize int
)
begin
    SELECT id,
           created_by,
           created_date,
           updated_by,
           updated_date,
           value
    FROM (SELECT *, ROW_NUMBER() over () as rn
          FROM log_entity
          order by created_date desc) as subquery
    WHERE rn between ((page - 1) * pageSize + 1) and (((page - 1) * pageSize) + pageSize);

end //
delimiter ;


delimiter //
create procedure get_post_by_id_of_user(
    id long
)
begin
    SELECT * FROM post_entity pe WHERE status = true AND pe.id = id;
end //
delimiter ;

DELIMITER //
CREATE PROCEDURE change_status_post(
    idPost LONG,
    updateBy VARCHAR(255),
    updateDate DATETIME(6)
)
BEGIN
    UPDATE post_entity
    SET status       = NOT status,
        updated_by   = updateBy,
        updated_date = updateDate
    WHERE id = idPost;
END //
DELIMITER ;


delimiter //
create procedure get_post_by_id_of_admin(
    id long
)
begin
    SELECT * FROM post_entity pe WHERE pe.id = id;
end //
delimiter ;


delimiter //
create procedure get_list_post_by_tag_id(
    tagId long,
    page int,
    pageSize int
)
begin
    SELECT id,
           content,
           created_by,
           created_date,
           status,
           thumbnail_img,
           title,
           updated_by,
           updated_date
    FROM (SELECT pe.id,
                 content,
                 created_by,
                 created_date,
                 status,
                 thumbnail_img,
                 title,
                 updated_by,
                 updated_date,
                 ROW_NUMBER() over () as rn
          FROM group_type gt
                   join post_entity pe on gt.post_id = pe.id
          where status = true
            and hash_tag_id = tagId
          group by gt.post_id
          order by created_date desc) as subquery
    WHERE rn between ((page - 1) * pageSize + 1) and (((page - 1) * pageSize) + pageSize);
end //
delimiter ;


delimiter //
CREATE PROCEDURE get_post_count(
    is_admin bit
)
BEGIN
    IF is_admin = 1 then
        SELECT COUNT(*)
        FROM post_entity;
    ELSE
        SELECT COUNT(*)
        FROM post_entity
        WHERE status = true;
    END IF;
End;
//
delimiter ;


