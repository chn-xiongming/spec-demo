package com.example.demo.service.business.user;

import com.example.demo.model.bo.user.UserBO;
import com.example.demo.model.vo.user.UserVO;
import com.example.demo.manager.business.user.UserManager;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserManager userManager;

    public UserServiceImpl(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public UserVO findUserById(Long id) {
        UserBO userBO = userManager.findUserById(id);
        return convertToVO(userBO);
    }

    @Override
    public UserVO findUserByUsername(String username) {
        UserBO userBO = userManager.findUserByUsername(username);
        return convertToVO(userBO);
    }

    @Override
    public Long createUser(UserBO userBO) {
        if (userManager.isUsernameExists(userBO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        return userManager.createUser(userBO);
    }

    @Override
    public void updateUser(UserBO userBO) {
        UserBO existingUser = userManager.findUserById(userBO.getId());
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (!existingUser.getUsername().equals(userBO.getUsername()) 
            && userManager.isUsernameExists(userBO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        userManager.updateUser(userBO);
    }

    @Override
    public void deleteUser(Long id) {
        UserBO existingUser = userManager.findUserById(id);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        userManager.deleteUser(id);
    }

    private UserVO convertToVO(UserBO userBO) {
        if (userBO == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userBO, userVO);
        return userVO;
    }
}
