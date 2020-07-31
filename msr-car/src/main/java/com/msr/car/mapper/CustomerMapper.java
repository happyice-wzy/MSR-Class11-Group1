package com.msr.car.mapper;

import com.msr.car.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2020-07-14
 */
public interface CustomerMapper extends BaseMapper<Customer> {
    @Select("select count(id) id , gmt_create from car_customer GROUP BY gmt_create HAVING gmt_create BETWEEN #{begin} AND #{end};")
    List<Customer> getChartData(Date begin, Date end);
    @Select("select count(id) id , customer_sex from car_customer GROUP BY customer_sex;")
    List<Customer> getEChartData();
}
