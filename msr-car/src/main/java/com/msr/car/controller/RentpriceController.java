package com.msr.car.controller;


import com.msr.car.entity.Rentprice;
import com.msr.car.service.RentpriceService;
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
@Api(description="租金")
@RestController
@RequestMapping("/car/rentprice")
public class RentpriceController {
    @Autowired
    private RentpriceService rentpriceService;

    @ApiOperation(value = "所有租金列表")
    @GetMapping("/list")
    public R list(){
        List<Rentprice> rentpriceList = rentpriceService.list(null);
        return R.ok().data("list", rentpriceList);
    }

    @ApiOperation(value = "根据ID删除租金")
    @DeleteMapping("/delete")
    public R removeByIds(
            @ApiParam(name = "ids", value = "租金ID", required = true)
            @RequestBody String[] ids){
        rentpriceService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @ApiOperation(value = "修改租金")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(name = "id", value = "租金ID", required = true)
            @RequestBody Rentprice rentprice){
        rentpriceService.updateById(rentprice);
        return R.ok();
    }

    @ApiOperation(value = "新增租金信息")
    @PostMapping("/save")
    public R save(
            @ApiParam(name = "rentprice", value = "租金对象", required = true)
            @RequestBody Rentprice rentprice) {
        rentpriceService.save(rentprice);
        return R.ok();
    }
    @ApiOperation(value = "根据ID查询租金")
    @GetMapping("/info/{id}")
    public R getById(
            @ApiParam(name = "id", value = "车型ID", required = true)
            @PathVariable String id){
        Rentprice rentprice = rentpriceService.getById(id);
        return R.ok().data("item", rentprice);
    }

}

