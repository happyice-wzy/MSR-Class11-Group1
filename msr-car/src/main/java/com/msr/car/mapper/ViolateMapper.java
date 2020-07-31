package com.msr.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msr.car.entity.Violate;
import org.apache.ibatis.annotations.Select;

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
public interface ViolateMapper extends BaseMapper<Violate> {
    @Select("select count(id) id , violate_date from car_violate GROUP BY violate_date HAVING violate_date BETWEEN #{begin} AND #{end};")
    List<Violate> getChartData(Date begin, Date end);
}
