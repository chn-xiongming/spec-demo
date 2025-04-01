package com.example.demo.repository.mapper;

import com.example.demo.model.po.project.ProjectPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ProjectMapper {
    
    @Insert({
        "INSERT INTO projects (",
            "name, description, start_date, end_date, status, created_by, created_at, updated_by, updated_at",
        ") VALUES (",
            "#{name}, #{description}, #{startDate}, #{endDate}, #{status}, #{createdBy}, #{createdAt}, #{updatedBy}, #{updatedAt}",
        ")"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ProjectPO project);
}
