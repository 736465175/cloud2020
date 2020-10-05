package com.lizhiqiang.springcloud.alibaba.service.impl;

import com.lizhiqiang.springcloud.alibaba.dao.OrderDao;
import com.lizhiqiang.springcloud.alibaba.service.AccountService;
import com.lizhiqiang.springcloud.alibaba.service.OrderService;
import com.lizhiqiang.springcloud.alibaba.service.StorageService;
import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Order;
import com.lizhiqiang.springcloud.entities.Storage;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    //微服务openFeign
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 创建订单->调用库存服务扣减库存 ->调用账户服务扣减余额 ->修改订单状态 ->end
     * @param order
     */
    @Override
    @GlobalTransactional(name = "lzq-order-create-tran", rollbackFor = Exception.class)
    public CommonResult<Order> create(Order order) {
        log.info("------>开始新建订单 start");
        orderDao.create(order);

        log.info("------>订单微服务开始调用库存微服务，减库存Count start");
        CommonResult<Storage> storage = storageService.decrease(order.getProductId(), order.getCount());
        log.info(storage.toString());
        log.info("------>订单微服务开始调用库存微服务，减库存Count end");

        log.info("----->订单微服务开始调用账户微服务，扣余额Money start");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------>订单微服务开始调用账户微服务，扣余额Money end");

        //4.修改订单状态，0到1 表示订单完成
        log.info("------>修改订单状态开始 start");
        orderDao.update(order.getUserId(), order.getProductId());
        log.info("------>修改订单状态开始 end");

        log.info("------>下订单全部 end");
        return new CommonResult<>(200, "订单创建完成", order);
    }

}
