package com.example.demo.service.business.project;

import com.example.demo.model.vo.project.ProjectVO;
import com.example.demo.web.project.command.ProjectCreateCommand;

public interface ProjectService {
    /**
     * Create a new project
     * @param command Project creation command
     * @return Created project view object
     */
    ProjectVO createProject(ProjectCreateCommand command);
}
