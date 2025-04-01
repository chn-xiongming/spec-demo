package com.example.demo.service.business.project;

import com.example.demo.model.po.project.ProjectPO;
import com.example.demo.model.vo.project.ProjectVO;
import com.example.demo.repository.mapper.ProjectMapper;
import com.example.demo.web.project.command.ProjectCreateCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    @Transactional
    public ProjectVO createProject(ProjectCreateCommand command) {
        // Convert command to PO
        ProjectPO projectPO = new ProjectPO();
        projectPO.setName(command.getName());
        projectPO.setDescription(command.getDescription());
        projectPO.setStartDate(command.getStartDate());
        projectPO.setEndDate(command.getEndDate());
        projectPO.setStatus("CREATED");
        
        // Set audit fields
        LocalDateTime now = LocalDateTime.now();
        projectPO.setCreatedAt(now);
        projectPO.setUpdatedAt(now);
        projectPO.setCreatedBy(1L); // TODO: Get from security context
        projectPO.setUpdatedBy(1L); // TODO: Get from security context

        // Save to database
        projectMapper.insert(projectPO);

        // Convert to VO and return
        ProjectVO projectVO = new ProjectVO();
        projectVO.setId(projectPO.getId());
        projectVO.setName(projectPO.getName());
        projectVO.setDescription(projectPO.getDescription());
        projectVO.setStartDate(projectPO.getStartDate());
        projectVO.setEndDate(projectPO.getEndDate());
        projectVO.setStatus(projectPO.getStatus());
        projectVO.setCreatedBy(projectPO.getCreatedBy());
        projectVO.setCreatedAt(projectPO.getCreatedAt());
        projectVO.setUpdatedBy(projectPO.getUpdatedBy());
        projectVO.setUpdatedAt(projectPO.getUpdatedAt());

        return projectVO;
    }
}
