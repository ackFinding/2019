package com.seckill.controller;

import com.seckill.controller.viewobject.ItemVO;
import com.seckill.error.BusinessException;
import com.seckill.response.CommonReturnType;
import com.seckill.service.ItemService;
import com.seckill.service.model.ItemModel;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller("item")
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    /**
     * 创建商品
     */
    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam("title") String title,
                                       @RequestParam("description") String description,
                                       @RequestParam("imgUrl") String imgUrl,
                                       @RequestParam("price") BigDecimal price,
                                       @RequestParam("stock") Integer stock) throws BusinessException {

        ItemModel itemModel = new ItemModel();
        itemModel.setStock(stock);
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);
        itemModel.setPrice(price);
        ItemModel itemModelForReturn = itemService.createItem(itemModel);
        ItemVO itemVO = convertItemModelToItemVO(itemModelForReturn);
        return CommonReturnType.create(itemVO);
    }

    /**
     * 商品详情页浏览（一般是GET方法,也就是对服务端不发任何变化的幂等操作）
     */
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam("id") Integer id) {
        ItemModel itemModel = itemService.getItemById(id);
        ItemVO itemVO = convertItemModelToItemVO(itemModel);
        return CommonReturnType.create(itemVO);
    }

    @RequestMapping(value = "/listItems", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listItems() {
        List<ItemModel> itemModels = itemService.listItems();
        List<ItemVO> itemVOS = itemModels.stream().map((itemModel) -> {
            ItemVO itemVO = convertItemModelToItemVO(itemModel);
            return itemVO;
        }).collect(Collectors.toList());
        return CommonReturnType.create(itemVOS);
    }

    private ItemVO convertItemModelToItemVO(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        if (itemModel.getPromoModel() != null) {
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setStartDate(itemModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
            itemVO.setPromoId(itemModel.getPromoModel().getId());
        }
        return itemVO;
    }

}
