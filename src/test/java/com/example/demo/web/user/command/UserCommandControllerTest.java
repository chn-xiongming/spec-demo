package com.example.demo.web.user.command;

import com.example.demo.model.bo.user.UserBO;
import com.example.demo.model.vo.user.UserVO;
import com.example.demo.service.business.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserCommandController.class)
public class UserCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUser_ShouldReturnUserId() throws Exception {
        // Given
        UserCreateCommand command = new UserCreateCommand();
        command.setUsername("testuser");
        command.setPassword("password123");
        command.setEmail("test@example.com");

        when(userService.createUser(any(UserBO.class))).thenReturn(1L);

        // When & Then
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
    }

    @Test
    void updateUser_ShouldSucceed() throws Exception {
        // Given
        UserUpdateCommand command = new UserUpdateCommand();
        command.setUsername("testuser");
        command.setPassword("newpassword123");
        command.setEmail("test@example.com");

        // When & Then
        mockMvc.perform(put("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser_ShouldSucceed() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isOk());
    }
}
