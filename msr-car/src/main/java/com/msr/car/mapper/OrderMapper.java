package com.msr.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msr.car.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {
    @Select("select count(id) id , gmt_create from car_order GROUP BY gmt_create HAVING gmt_create BETWEEN #{begin} AND #{end};")
    List<Order> getChartData(Date begin, Date end);
}
