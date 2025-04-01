package com.example.demo.web.project.command;

import com.example.demo.model.vo.project.ProjectVO;
import com.example.demo.service.business.project.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectCommandController {

    private final ProjectService projectService;

    public ProjectCommandController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectVO> createProject(
            @Valid @RequestBody ProjectCreateCommand command,
            BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            command.validate();
            ProjectVO projectVO = projectService.createProject(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(projectVO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
