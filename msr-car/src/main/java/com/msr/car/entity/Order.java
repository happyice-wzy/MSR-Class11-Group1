package com.msr.car.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("car_order")
@ApiModel(value="Order对象", description="")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "全局统一标识符")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "客户编号")
    private String customerId;

    @ApiModelProperty(value = "车辆品牌")
    private String carBrand;

    @ApiModelProperty(value = "车牌号")
    private String carNo;

    @ApiModelProperty(value = "驾驶证号")
    private String licenseNo;

    @ApiModelProperty(value = "开始时间")
    private Date orderStart;

    @ApiModelProperty(value = "计划还车时间")
    private Date orderPEnd;

    @ApiModelProperty(value = "实际还车时间")
    private Date orderAEnd;

    @ApiModelProperty(value = "基本消费")
    private Float orderBasic;

    @ApiModelProperty(value = "超时金额")
    private Float orderTimeout;

    @ApiModelProperty(value = "租车押金")
    private Float orderDeposit;

    @ApiModelProperty(value = "违章罚款")
    private Float orderViolate;

    @ApiModelProperty(value = "车损扣款")
    private Float orderUllage;

    @ApiModelProperty(value = "总消费金额")
    private Float orderSum;

    @ApiModelProperty(value = "租赁状态（1、已租出2、在库）")
    private Boolean priceStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


}
