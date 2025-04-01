package com.example.demo.repository.mapper;

import com.example.demo.model.po.user.UserPO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    
    @Select("SELECT * FROM users WHERE id = #{id} and 1=1")
    UserPO findById(Long id);
    
    @Select("SELECT * FROM users WHERE username = #{username}")
    UserPO findByUsername(String username);
    
    @Insert("INSERT INTO users (username, password, email) VALUES (#{username}, #{password}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(UserPO userPO);
    
    @Update("UPDATE users SET username = #{username}, password = #{password}, email = #{email} WHERE id = #{id}")
    void update(UserPO userPO);
    
    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteById(Long id);
}
