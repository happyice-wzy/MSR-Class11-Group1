package com.msr.car.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_model")
@ApiModel(value="Model对象", description="")
public class Model2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "车辆品牌")
    private String modelBrand;

    @ApiModelProperty(value = "在库数量")
    private Integer modelCount;


}
