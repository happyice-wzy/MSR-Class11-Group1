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
@TableName("car_model")
@ApiModel(value="Model对象", description="")
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "全局统一标识符")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "车辆品牌")
    private String modelBrand;

    @ApiModelProperty(value = "车辆类型")
    private String modelType;

    @ApiModelProperty(value = "座位数")
    private Integer modelSeatnum;

    @ApiModelProperty(value = "油箱容量")
    private Integer modelFuelTank;

    @ApiModelProperty(value = "记录创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "在库数量")
    private String modelCount;


}
