package com.msr.car.service.impl;

import com.msr.car.entity.Customer;
import com.msr.car.mapper.CustomerMapper;
import com.msr.car.service.CustomerService;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
