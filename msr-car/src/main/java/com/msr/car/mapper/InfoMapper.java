package com.msr.car.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msr.car.entity.Info;
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
public interface InfoMapper extends BaseMapper<Info> {
    @Select("select count(id) id , gmt_create from car_info GROUP BY gmt_create HAVING gmt_create BETWEEN #{begin} AND #{end};")
    List<Info> getChartData(Date begin, Date end);
}
