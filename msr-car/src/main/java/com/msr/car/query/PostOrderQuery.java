package com.msr.car.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;

import com.msr.car.entity.PostOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@ApiModel(value = "续租订单查询对象",description = "续租订单查询对象封装")
@Data
public class PostOrderQuery extends Wrapper<PostOrder> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "根据续租主订单id查询")
    private  String id;


    @Override
    public PostOrder getEntity() {
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
