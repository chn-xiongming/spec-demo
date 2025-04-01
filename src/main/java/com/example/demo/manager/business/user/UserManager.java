package com.example.demo.manager.business.user;

import com.example.demo.model.bo.user.UserBO;

public interface UserManager {
    
    /**
     * Find user by ID
     * @param id user ID
     * @return user business object
     */
    UserBO findUserById(Long id);

    /**
     * Find user by username
     * @param username username
     * @return user business object
     */
    UserBO findUserByUsername(String username);

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

    /**
     * Check if username exists
     * @param username username
     * @return true if username exists, false otherwise
     */
    boolean isUsernameExists(String username);
}
