package com.msr.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.car.entity.Customer;
import com.msr.car.mapper.CustomerMapper;
import com.msr.car.query.CustomerQuery;
import com.msr.car.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tom
 * @since 2020-07-14
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Override
    public void pageQuery(Page<Customer> pageParam, CustomerQuery customerQuery) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        if(customerQuery == null){
            baseMapper.selectPage(pageParam,queryWrapper);
            return;
        }
        String customerName = customerQuery.getCustomerName();

        String begin = customerQuery.getBegin();
        String end = customerQuery.getEnd();
        if(!StringUtils.isEmpty(customerName)){
            queryWrapper.like("customer_name",customerName);
        }

        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }
        baseMapper.selectPage(pageParam,queryWrapper);
    }
}
