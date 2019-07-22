package com.seckill.controller;

import com.seckill.controller.viewobject.UserVO;
import com.seckill.error.BusinessErrorTypeEnum;
import com.seckill.error.BusinessException;
import com.seckill.response.CommonReturnType;
import com.seckill.service.UserService;
import com.seckill.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")//跨域请求中，不能做到session共享
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    //通过threadLocal实现按单例注入
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 用户登陆接口
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE})
    @ResponseBody
    public CommonReturnType login(@RequestParam("phone") String phone,
                                  @RequestParam("password") String password) throws BusinessException, NoSuchAlgorithmException {

        //入参校验
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            throw new BusinessException(BusinessErrorTypeEnum.PARAM_VALIDATION_ERROR);
        }

        //验证是否合法
        UserModel userModel = userService.validateLogin(phone, encodeByMD5(password));

        //将登陆凭证加入登陆成功的session
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(null);
    }

    /**
     * 用户注册接口
     */
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE})
    @ResponseBody
    public CommonReturnType register(@RequestParam("otpCode") String otpCode,
                                     @RequestParam("name") String name,
                                     @RequestParam("gender") Integer gender,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("phone") String phone,
                                     @RequestParam("password") String password) throws BusinessException, NoSuchAlgorithmException {
        //验证手机号和验证码
        String sessionOtp = (String) httpServletRequest.getSession().getAttribute(phone);
        if (!StringUtils.equals(otpCode, sessionOtp)) {
            throw new BusinessException(BusinessErrorTypeEnum.PARAM_VALIDATION_ERROR, "短信验证失败");
        }
        //注册
        UserModel userModel = new UserModel();
        userModel.setAge(age);
        userModel.setName(name);
        userModel.setGender(new Byte(String.valueOf(gender)));
        userModel.setPhone(phone);
        userModel.setEncryptPassword(encodeByMD5(password));
        userModel.setRegisterType("byphone");
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    private String encodeByMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encodedStr = base64Encoder.encode(md5.digest(password.getBytes()));
        return encodedStr;
    }


    /**
     * 用户获取otp短信
     */
    @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam("phone") String phone) {
        //生成Otp
        Random random = new Random();
        int randInt = random.nextInt(9999);
        randInt += 10000;
        String otpCode = String.valueOf(randInt);
        //将O与用户手机号关联起来tp(分布式下使用redis更好)
        httpServletRequest.getSession().setAttribute(phone, otpCode);
        //通过短信通道发送验证码
        System.out.println("phone[" + phone + "],otp[" + otpCode + "]");
        return CommonReturnType.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
            throw new BusinessException(BusinessErrorTypeEnum.USER_NOT_EXIST);
        }
        UserVO userVO = convertFromUserModel(userModel);
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromUserModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }


}
