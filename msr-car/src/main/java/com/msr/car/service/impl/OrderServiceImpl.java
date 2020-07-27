package com.msr.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msr.car.entity.Order;
import com.msr.car.mapper.OrderMapper;
import com.msr.car.query.OrderQuery;
import com.msr.car.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public void pageQuery(Page<Order> pageParam, OrderQuery orderQuery) {
        //条件构造器
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");

        if (orderQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String id = orderQuery.getId();


        if (!StringUtils.isEmpty(id)) {
            queryWrapper.eq("id", id);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }
}
