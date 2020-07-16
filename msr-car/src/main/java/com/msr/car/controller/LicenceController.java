package com.msr.car.controller;


import com.msr.car.entity.Licence;
import com.msr.car.service.LicenceService;
import com.msr.common.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
@Api(description="驾驶证管理")
@RestController
@RequestMapping("/car/licence")
public class LicenceController {
    @Autowired
    private LicenceService licenceService;

    @ApiOperation(value = "所有驾驶证列表")
    @GetMapping("/list")
    public R list(){
        List<Licence> licenceList = licenceService.list(null);
        return R.ok().data("list",licenceList);
    }

    @ApiOperation(value = "根据ID删除驾驶证")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "驾驶证ID", required = true)
            @PathVariable String id){
        licenceService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "新增驾驶证")
    @PostMapping("/save")
    public R save(
            @ApiParam(name = "licence", value = "驾驶证对象", required = true)
            @RequestBody Licence licence){
        licenceService.save(licence);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询驾驶证")
    @GetMapping("/info/{id}")
    public R getById(
            @ApiParam(name = "id", value = "驾驶证id", required = true)
            @PathVariable String id){
        Licence licence = licenceService.getById(id);
        return R.ok().data("item",licence);
    }

    @ApiOperation(value = "根据ID修改驾驶证")
    @PutMapping("/update")
    public R updateById(
            //@ApiParam(name="id", value = "驾驶证id",required = true)
            //@PathVariable String id,
            @ApiParam(name = "licence",value = "驾驶证对象",required = true)
            @RequestBody Licence licence){
        //licence.setId(id);
        licenceService.updateById(licence);
        return  R.ok();
    }
}

