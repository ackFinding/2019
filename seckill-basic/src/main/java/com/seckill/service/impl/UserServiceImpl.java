package com.seckill.service.impl;

import com.seckill.dao.UserDOMapper;
import com.seckill.dao.UserPasswordDOMapper;
import com.seckill.dataobject.UserDO;
import com.seckill.dataobject.UserPasswordDO;
import com.seckill.error.BusinessErrorTypeEnum;
import com.seckill.error.BusinessException;
import com.seckill.service.UserService;
import com.seckill.service.model.UserModel;
import com.seckill.validator.ValidationResult;
import com.seckill.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null) {
            return null;
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        return convertFromDataObject(userDO, userPasswordDO);
    }

    @Transactional
    @Override
    public void register(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            throw new BusinessException(BusinessErrorTypeEnum.PARAM_VALIDATION_ERROR);
        }
        ValidationResult result = validator.validate(userModel);
        if (result.isError()) {
            throw new BusinessException(BusinessErrorTypeEnum.PARAM_VALIDATION_ERROR, result.getErrorMsg());
        }
        UserDO userDO = convertFromUserModelToUserDO(userModel);
        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessErrorTypeEnum.PARAM_VALIDATION_ERROR, "该手机号已注册，不可重复注册");
        }

        userModel.setId(userDO.getId());
        UserPasswordDO userPasswordDO = convertFromUserModelToUserPasswordDO(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
    }

    @Override
    public UserModel validateLogin(String phone, String encryptPassword) throws BusinessException {
        //获取对应用户信息
        UserDO userDO = userDOMapper.selectByPhone(phone);
        if (userDO == null) {
            throw new BusinessException(BusinessErrorTypeEnum.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO, userPasswordDO);
        //校验密码
        if (!StringUtils.equals(userModel.getEncryptPassword(), encryptPassword)) {
            throw new BusinessException(BusinessErrorTypeEnum.USER_LOGIN_FAIL);
        }
        return userModel;
    }

    private UserPasswordDO convertFromUserModelToUserPasswordDO(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setUserId(userModel.getId());
        userPasswordDO.setEncryptPassword(userModel.getEncryptPassword());
        return userPasswordDO;
    }

    private UserDO convertFromUserModelToUserDO(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if (userPasswordDO != null) {
            userModel.setEncryptPassword(userPasswordDO.getEncryptPassword());
        }
        return userModel;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encodedStr = base64Encoder.encode(md5.digest("123".getBytes()));
        System.out.println(encodedStr);
    }
}
