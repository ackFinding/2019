package com.seckill.controller;

import com.seckill.error.BusinessErrorTypeEnum;
import com.seckill.error.BusinessException;
import com.seckill.response.CommonReturnType;
import com.seckill.service.OrderService;
import com.seckill.service.model.OrderModel;
import com.seckill.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller("order")
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class OrderController extends BaseController {

    @Autowired
    OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/createOrder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam("itemId") Integer itemId, @RequestParam("amount") Integer amount,
                                        @RequestParam(value = "promoId", required = false) Integer promoId) throws BusinessException {
        Boolean login = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (login == null || login == false) {
            throw new BusinessException(BusinessErrorTypeEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        if (userModel == null) {
            throw new BusinessException(BusinessErrorTypeEnum.UNKNOWN_ERROR);
        }
        OrderModel orderModel = orderService.createOrder(userModel.getId(), itemId, amount,promoId);
        return CommonReturnType.create(null);
    }

}
