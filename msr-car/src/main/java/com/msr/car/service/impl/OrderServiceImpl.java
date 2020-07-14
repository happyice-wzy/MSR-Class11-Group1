package com.msr.car.service.impl;

import com.msr.car.entity.Order;
import com.msr.car.mapper.OrderMapper;
import com.msr.car.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
