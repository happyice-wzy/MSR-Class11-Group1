package com.msr.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msr.car.entity.Model;
import com.msr.car.entity.Model2;
import org.apache.ibatis.annotations.Select;

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
    @Select("select model_brand , model_count from car_model;")
    List<Model2> getChartData();
}
