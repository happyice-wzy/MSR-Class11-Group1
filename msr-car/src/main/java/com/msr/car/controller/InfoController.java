package com.msr.car.controller;


import com.msr.car.entity.Info;
import com.msr.car.service.InfoService;
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
@Api(description="车辆管理")
@RestController
@RequestMapping("/car/info")
public class InfoController {
    @Autowired
    private InfoService infoService;

    @ApiOperation(value = "所有车辆列表")
    @GetMapping("/list")
    public R list(){
        List<Info> infoList = infoService.list(null);
        return R.ok().data("list", infoList);
    }

    @ApiOperation(value = "根据ID删除车辆")
    @DeleteMapping("/delete")
    public R removeByIds(
            @ApiParam(name = "ids", value = "车辆ID", required = true)
            @RequestBody String[] ids){
        infoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @ApiOperation(value = "修改车辆")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(name = "id", value = "车辆ID", required = true)
            @RequestBody Info info){
        infoService.updateById(info);
        return R.ok();
    }

    @ApiOperation(value = "新增汽车基本信息")
    @PostMapping("/save")
    public R save(
            @ApiParam(name = "info", value = "汽车对象", required = true)
            @RequestBody Info info){
        infoService.save(info);
        return R.ok();
    }
    @ApiOperation(value = "根据ID查询车辆")
    @GetMapping("/info/{id}") //路径传参
    public R getById(
            @ApiParam(name = "id", value = "车辆ID", required = true)
            @PathVariable String id){
        Info info = infoService.getById(id);
        return R.ok().data("item", info);
    }
}

