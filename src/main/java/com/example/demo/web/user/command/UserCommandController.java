package com.example.demo.web.user.command;

import com.example.demo.model.bo.user.UserBO;
import com.example.demo.service.business.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserCommandController {

    private final UserService userService;

    public UserCommandController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody UserCreateCommand command) {
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(command, userBO);
        return userService.createUser(userBO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long id, @RequestBody UserUpdateCommand command) {
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(command, userBO);
        userBO.setId(id);
        userService.updateUser(userBO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
