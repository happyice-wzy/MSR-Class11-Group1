package com.msr.car.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gzb
 * @version V1.0
 * @Package com.msr.car.query
 * @date 2020/7/17 13:55
 * @Copyright © 株式会社多言语系统研究所
 */
@ApiModel(value = "Model查询对象", description = "车型查询对象封装")
@Data
public class ViolateQuery {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "根据车辆id查询")
    private String id;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
