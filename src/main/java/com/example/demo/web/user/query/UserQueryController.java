package com.example.demo.web.user.query;

import com.example.demo.model.bo.user.UserBO;
import com.example.demo.model.vo.user.UserVO;
import com.example.demo.service.business.user.UserService;
import com.example.demo.web.user.command.UserCreateCommand;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserQueryController {

    private final UserService userService;

    public UserQueryController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserVO getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/username/{username}")
    public UserVO getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody UserCreateCommand command) {
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(command, userBO);
        return userService.createUser(userBO);
    }
}
