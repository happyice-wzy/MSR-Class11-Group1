package com.msr.car.controller;


import com.msr.car.entity.Model;
import com.msr.car.service.ModelService;
import com.msr.common.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
@Api(description="车型管理")
@RestController
@RequestMapping("/car/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @ApiOperation(value = "所有车型列表")
    @GetMapping("/list")
    public R list(){
        List<Model> modelList = modelService.list(null);
        return R.ok().data("list", modelList);
    }

    @ApiOperation(value = "根据ID删除车型")
    @DeleteMapping("/delete")
    public R removeByIds(
            @ApiParam(name = "ids", value = "车型ID", required = true)
            @RequestBody Long[] ids){
        modelService.removeByIds(Arrays.asList(ids));
        return R.ok();
}

    @ApiOperation(value = "修改车辆")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(name = "id", value = "车型ID", required = true)
            @RequestBody Model model){
        modelService.updateById(model);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("/info/{id}")
    public R getById(
            @ApiParam(name = "id", value = "车型ID", required = true)
            @PathVariable String id){

        Model model = modelService.getById(id);
        return R.ok().data("item", model);
    }
}

