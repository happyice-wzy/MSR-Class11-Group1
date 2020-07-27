package com.msr.car.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.car.entity.Model;
import com.msr.car.query.ModelQuery;
import com.msr.car.service.ModelService;
import com.msr.common.constants.ResultCodeEnum;
import com.msr.common.exception.MSRException;
import com.msr.common.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "分页车型列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "modelQuery", value = "查询对象", required = false)
                    ModelQuery modelQuery,

            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        if(page <= 0 || limit <= 0){
            //throw new MSRException(21003, "参数不正确1");
            throw new MSRException(ResultCodeEnum.PARAM_ERROR);
        }

        Page<Model> pageParam = new Page<>(page, limit);

        //teacherService.page(pageParam, null);
        modelService.pageQuery(pageParam,modelQuery);

        List<Model> records = pageParam.getRecords();//当前页的集合数据
        long total = pageParam.getTotal();   //总记录数

        return  R.ok().data("total", total).data("rows", records);//返回数据
    }


    @ApiOperation(value = "根据ID删除车型")
    @DeleteMapping("/delete")
    public R removeByIds(
            @ApiParam(name = "ids", value = "车型ID", required = true)
            @RequestBody String[] ids){
        modelService.removeByIds(Arrays.asList(ids));
        return R.ok();
}

    @ApiOperation(value = "修改车型")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(name = "id", value = "车型ID", required = true)
            @RequestBody Model model){
        modelService.updateById(model);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询车型")
    @GetMapping("/info/{id}")
    public R getById(
            @ApiParam(name = "id", value = "车型ID", required = true)
            @PathVariable String id){

        Model model = modelService.getById(id);
        return R.ok().data("item", model);
    }
    @ApiOperation(value = "新增车型信息")
    @PostMapping("/save")
    public R save(
            @ApiParam(name = "model", value = "车型对象", required = true)
            @RequestBody Model model){
        modelService.save(model);
        return R.ok();
    }

    @GetMapping("/show-chart")
    public R showChart(){
        Map<String, Object> map = modelService.getChartData();
        return R.ok().data(map);
    }
}

