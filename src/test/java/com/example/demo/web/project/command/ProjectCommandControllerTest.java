package com.example.demo.web.project.command;

import com.example.demo.config.TestConfig;
import com.example.demo.model.vo.project.ProjectVO;
import com.example.demo.service.business.project.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectCommandController.class)
@Import(TestConfig.class)
class ProjectCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProjectService projectService;

    @Test
    void createProject_WithValidData_ShouldReturnCreatedProject() throws Exception {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        ProjectCreateCommand command = new ProjectCreateCommand();
        command.setName("Test Project");
        command.setDescription("Test Description");
        command.setStartDate(now);
        command.setEndDate(now.plusMonths(1));

        ProjectVO expectedResponse = new ProjectVO();
        expectedResponse.setId(1L);
        expectedResponse.setName(command.getName());
        expectedResponse.setDescription(command.getDescription());
        expectedResponse.setStartDate(command.getStartDate());
        expectedResponse.setEndDate(command.getEndDate());
        expectedResponse.setStatus("CREATED");

        when(projectService.createProject(any(ProjectCreateCommand.class))).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(command.getName()))
                .andExpect(jsonPath("$.description").value(command.getDescription()))
                .andExpect(jsonPath("$.status").value("CREATED"));

        verify(projectService).createProject(any(ProjectCreateCommand.class));
    }

    @Test
    void createProject_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        // Arrange
        ProjectCreateCommand command = new ProjectCreateCommand();
        // Missing required fields

        // Act & Assert
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createProject_WithEndDateBeforeStartDate_ShouldReturnBadRequest() throws Exception {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        ProjectCreateCommand command = new ProjectCreateCommand();
        command.setName("Test Project");
        command.setDescription("Test Description");
        command.setStartDate(now);
        command.setEndDate(now.minusDays(1)); // End date before start date

        // Act & Assert
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isBadRequest());
    }
}
