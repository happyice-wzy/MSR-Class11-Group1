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
@TableName("car_info")
@ApiModel(value="Info对象", description="")
public class Info implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "全局统一标识符")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "ModelInfo的ID")
    @TableField("modelInfo_id")
    private String modelinfoId;

    @ApiModelProperty(value = "车牌号")
    private String carNo;

    @ApiModelProperty(value = "车辆颜色")
    private String carColor;

    @ApiModelProperty(value = "车辆照片")
    private String carPhoto;

    @ApiModelProperty(value = "燃油编号")
    private String carFuelno;

    @ApiModelProperty(value = "描述")
    private String carDesc;

    @ApiModelProperty(value = "记录创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "所在门店编号")
    private String storeId;

    @ApiModelProperty(value = "所在门店")
    private String storeArea;


}
