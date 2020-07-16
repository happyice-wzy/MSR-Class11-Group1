package com.msr.car.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msr.car.entity.Customer;
import com.msr.car.query.CustomerQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
public interface CustomerService extends IService<Customer> {
    void pageQuery(Page<Customer> pageParam, CustomerQuery teacherQuery);
}
