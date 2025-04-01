package com.example.demo.service.business.user;

import com.example.demo.model.bo.user.UserBO;
import com.example.demo.model.vo.user.UserVO;

public interface UserService {
    
    /**
     * Find user by ID
     * @param id user ID
     * @return user view object
     */
    UserVO findUserById(Long id);

    /**
     * Find user by username
     * @param username username
     * @return user view object
     */
    UserVO findUserByUsername(String username);

    /**
     * Create a new user
     * @param userBO user business object
     * @return created user ID
     */
    Long createUser(UserBO userBO);

    /**
     * Update existing user
     * @param userBO user business object
     */
    void updateUser(UserBO userBO);

    /**
     * Delete user by ID
     * @param id user ID
     */
    void deleteUser(Long id);
}
