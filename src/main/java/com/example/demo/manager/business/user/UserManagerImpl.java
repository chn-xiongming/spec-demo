package com.example.demo.manager.business.user;

import com.example.demo.model.bo.user.UserBO;
import com.example.demo.model.po.user.UserPO;
import com.example.demo.repository.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserManagerImpl implements UserManager {

    private final UserMapper userMapper;

    public UserManagerImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserBO findUserById(Long id) {
        UserPO userPO = userMapper.findById(id);
        return convertToBO(userPO);
    }

    @Override
    public UserBO findUserByUsername(String username) {
        UserPO userPO = userMapper.findByUsername(username);
        return convertToBO(userPO);
    }

    @Override
    public Long createUser(UserBO userBO) {
        UserPO userPO = convertToPO(userBO);
        userMapper.insert(userPO);
        return userPO.getId();
    }

    @Override
    public void updateUser(UserBO userBO) {
        UserPO userPO = convertToPO(userBO);
        userMapper.update(userPO);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userMapper.findByUsername(username) != null;
    }

    private UserBO convertToBO(UserPO userPO) {
        if (userPO == null) {
            return null;
        }
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(userPO, userBO);
        return userBO;
    }

    private UserPO convertToPO(UserBO userBO) {
        if (userBO == null) {
            return null;
        }
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userBO, userPO);
        return userPO;
    }
}
