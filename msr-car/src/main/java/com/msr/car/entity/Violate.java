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
@TableName("car_violate")
@ApiModel(value="Violate对象", description="")
public class Violate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "全局统一标识符")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "违章车辆标志号")
    private String carInfoId;

    @ApiModelProperty(value = "违章时间")
    private Date violateDate;

    @ApiModelProperty(value = "违章事件")
    private String violateEvent;

    @ApiModelProperty(value = "扣分")
    private Float violateMarks;

    @ApiModelProperty(value = "违章罚款")
    private Float violateFee;

    @ApiModelProperty(value = "状态")
    private Boolean violateStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


}
