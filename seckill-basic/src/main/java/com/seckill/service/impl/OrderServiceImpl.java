package com.seckill.service.impl;

import com.seckill.dao.OrderDOMapper;
import com.seckill.dao.SequenceDOMapper;
import com.seckill.dataobject.OrderDO;
import com.seckill.dataobject.SequenceDO;
import com.seckill.error.BusinessErrorTypeEnum;
import com.seckill.error.BusinessException;
import com.seckill.service.ItemService;
import com.seckill.service.OrderService;
import com.seckill.service.UserService;
import com.seckill.service.model.ItemModel;
import com.seckill.service.model.OrderModel;
import com.seckill.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount, Integer promoId) throws BusinessException {
        //1.校验下单状态，下单的商品是否存在，用户是否合法，购买数量是否正确
        ItemModel itemModel = itemService.getItemById(itemId);
        if (itemModel == null) {
            throw new BusinessException(BusinessErrorTypeEnum.PARAM_VALIDATION_ERROR, "商品信息不存在");
        }
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) {
            throw new BusinessException(BusinessErrorTypeEnum.PARAM_VALIDATION_ERROR, "用户信息不存在");
        }
        //限购
        if (amount <= 0 || amount > 99) {
            throw new BusinessException(BusinessErrorTypeEnum.PARAM_VALIDATION_ERROR, "数量参数错误");
        }
        //2.验证活动信息
        if (promoId != null) {
            //2.1验证盖商品是否存在秒杀活动
            if (itemModel.getPromoModel() == null || itemModel.getPromoModel().getId() != promoId) {
                throw new BusinessException(BusinessErrorTypeEnum.PARAM_VALIDATION_ERROR, "活动信息错误");
            } else if (itemModel.getPromoModel().getStatus() != 2) {//2.2验证是否处于活动时间
                throw new BusinessException(BusinessErrorTypeEnum.PARAM_VALIDATION_ERROR, "活动尚未开始");
            }
        }
        //3.下单减库存
        boolean success = itemService.decreaseStock(itemId, amount);
        if (!success) {
            throw new BusinessException(BusinessErrorTypeEnum.STOCK_NOT_ENOUGH);
        }
        //4.订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setAmount(amount);
        orderModel.setItemId(itemId);
        orderModel.setPromoId(promoId);
        if (promoId != null) {
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else {
            orderModel.setItemPrice(itemModel.getPrice());
        }
        orderModel.setUserId(userId);
        orderModel.setId(generateOrderNo());
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(BigDecimal.valueOf(amount)));

        OrderDO orderDO = convertOrderModelToOrderDO(orderModel);
        orderDOMapper.insertSelective(orderDO);
        //销量增加
        itemService.increaseSales(itemId, amount);
        return orderModel;
    }

    private OrderDO convertOrderModelToOrderDO(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        orderDO.setItemPrice(orderModel.getItemPrice().doubleValue());
        orderDO.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        return orderDO;
    }


    /**
     * 生成订单号
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateOrderNo() {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        //前8:日期
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(nowDate);
        // 中间6自增序列
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        int sequence = sequenceDO.getCurrenValue();
        sequenceDO.setCurrenValue(sequence + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKey(sequenceDO);
        String sequenceStr = String.valueOf(sequence);
        for (int i = 0; i < 6 - sequenceStr.length(); i++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);
        //todo: 后2:分库分表位(暂不处理)
        stringBuilder.append("00");
        return stringBuilder.toString();
    }
}
