package com.msr.car.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.car.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msr.car.query.OrderQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
public interface OrderService extends IService<Order> {
    void pageQuery(Page<Order> pageParam, OrderQuery orderQuery);

    Map<String, List> getChartData(String begin, String end);
}
