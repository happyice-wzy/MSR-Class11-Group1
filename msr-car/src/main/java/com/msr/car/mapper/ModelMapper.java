package com.msr.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msr.car.entity.Model;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
public interface ModelMapper extends BaseMapper<Model> {
    //查询所有的用户及地址下所有的订单信息
    @Results({
            @Result(id = true, column = "id",property = "id"),
            @Result(column = "model_brand",property = "model_brand"),
            @Result(column = "model_type",property = "model_type"),
            @Result(column = "model_seatnum",property = "model_seatnum"),
            @Result(column = "model_fuel_tank",property = "model_fuel_tank"),
            @Result(column = "model_count",property = "model_count"),
            @Result(column = "gmt_create",property = "gmtCreate"),


    })
    @Select("select * from car_model")
    List<Model> getModel();
}
