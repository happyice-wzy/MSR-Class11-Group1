package com.msr.car.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@TableName("car_rentprice")
@ApiModel(value="Rentprice对象", description="")
public class Rentprice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "全局统一标识符")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "参考ModelInfo的UUID")
    @TableField("modeInfo_id")
    private String modeinfoId;

    @ApiModelProperty(value = "车损押金")
    private Float ullageDeposit;

    @ApiModelProperty(value = "日租金")
    @TableField("price_dailyM")
    private Float priceDailym;

    @ApiModelProperty(value = "超时费用")
    private Float priceOvertime;



    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


}
