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
@TableName("car_post_order")
@ApiModel(value="PostOrder对象", description="")
public class PostOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "全局统一标识符")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "续租的主订单号")
    private String orderId;

    @ApiModelProperty(value = "开始时间")
    private Date postOrderStart;

    @ApiModelProperty(value = "结束时间")
    private Date postOrderEnd;

    @ApiModelProperty(value = "续租金额")
    private Float postOrderDeposit;

    @ApiModelProperty(value = "续租状态 1（true）以续租， 0（false）续租")
    private Boolean postOrderStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


}
