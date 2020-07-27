package com.msr.car.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hutengfei
 * @version V1.0
 * @Package com.msr.car.query
 * @date 2020/7/14 11:00
 * @Copyright © 株式会社多言语系统研究所
 */
@ApiModel(value = "Customer查询对象",description = "客户查询对象封装")
@Data
public class CustomerQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value ="客户名称，模糊查询" )
    private String customerName;


    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间",example = "2019-12-01 10:10:10")
    private String end;
}
