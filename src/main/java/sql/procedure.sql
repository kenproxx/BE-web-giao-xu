delimiter //
create procedure find_post_paging(
    page int,
    pageSize int
)
begin
SELECT id, content, created_by, created_date, status, thumbnail_img, title, updated_by, updated_date
FROM (SELECT *, ROW_NUMBER() over () as rn
      FROM post_entity
      where status = true
        and created_date != (SELECT MAX(created_date) FROM post_entity WHERE status = true)
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