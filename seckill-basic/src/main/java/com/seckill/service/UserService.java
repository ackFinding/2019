package com.seckill.service;

import com.seckill.error.BusinessException;
import com.seckill.service.model.UserModel;

public interface UserService {

    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    /**
     *
     * @param phone
     * @param encryptPassword 加密后的密码
     * @throws BusinessException
     */
    UserModel validateLogin(String phone, String encryptPassword) throws BusinessException;
}
