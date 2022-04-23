package com.bwgjoseph.springbootsleuthzipkintracing.post;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PostMapper {

    @Select("SELECT * FROM POST WHERE id = #{id}")
    public Post get(Integer id);

    // Specifying `keyProperty` would set the `id` property to the `Post` object
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO POST(title, body, createdAt, createdBy, updatedAt, updatedBy) VALUES(#{title}, #{body}, #{createdAt}, #{createdBy}, #{updatedAt}, #{updatedBy})")
    // `Integer` that is returned is not the `id` of the inserted object but the number of inserted rows
    public Integer create(Post post);
}
