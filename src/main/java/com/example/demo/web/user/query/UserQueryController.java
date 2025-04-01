package com.example.demo.web.user.query;

import com.example.demo.model.vo.user.UserVO;
import com.example.demo.service.business.user.UserService;
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
}
