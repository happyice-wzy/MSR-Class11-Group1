package com.msr.car.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.msr.car.entity.Order;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "Teacher查询对象",description = "讲师查询对象封装")
@Data
public class OrderQuery extends Wrapper<Order> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "根据订单id查询")
    private  String id;


    /**
     * <p>
     * 实体对象（子类实现）
     * </p>
     *
     * @return 泛型 T
     */
    @Override
    public Order getEntity() {
        return null;
    }

    /**
     * 获取 MergeSegments
     */
    @Override
    public MergeSegments getExpression() {
        return null;
    }

    /**
     * SQL 片段
     */
    @Override
    public String getSqlSegment() {
        return null;
    }
}
