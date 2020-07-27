package com.msr.car.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.car.entity.Violate;
import com.msr.car.query.ViolateQuery;
import com.msr.car.service.ViolateService;
import com.msr.common.constants.ResultCodeEnum;
import com.msr.common.exception.MSRException;
import com.msr.common.vo.R;
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
@RestController
@RequestMapping("/car/violate")
public class ViolateController {
    @Autowired
    private ViolateService violateService;

    @ApiOperation(value = "所有违章列表")
    @GetMapping("/list")
    public R list(){
        List<Violate> violateList = violateService.list(null);
        return R.ok().data("list", violateList);
    }

    @ApiOperation(value = "根据ID删除违章信息")
    @DeleteMapping("/delete")
    public R removeByIds(
            @ApiParam(name = "ids", value = "车辆ID", required = true)
            @RequestBody String[] ids){
        violateService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @ApiOperation(value = "修改违章信息")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(name = "id", value = "车辆ID", required = true)
            @RequestBody Violate violate){
        violateService.updateById(violate);
        return R.ok();
    }

    @ApiOperation(value = "新增违章信息")
    @PostMapping("/save")
    public R save(
            @ApiParam(name = "violate", value = "违章对象", required = true)
            @RequestBody Violate violate){
        violateService.save(violate);
        return R.ok();
    }
    @ApiOperation(value = "根据ID查询违章信息")
    @GetMapping("/violate/{id}") //路径传参
    public R getById(
            @ApiParam(name = "id", value = "违章ID", required = true)
            @PathVariable String id){
        Violate violate = violateService.getById(id);
        return R.ok().data("item", violate);
    }

    @ApiOperation(value = "分页违章列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "violateQuery", value = "查询对象", required = false)
                    ViolateQuery violateQuery,

            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        if(page <= 0 || limit <= 0){
            //throw new MSRException(21003, "参数不正确1");
            throw new MSRException(ResultCodeEnum.PARAM_ERROR);
        }

        Page<Violate> pageParam = new Page<>(page, limit);

        //teacherService.page(pageParam, null);
        violateService.pageQuery(pageParam,violateQuery);

        List<Violate> records = pageParam.getRecords();//当前页的集合数据
        long total = pageParam.getTotal();   //总记录数

        return  R.ok().data("total", total).data("rows", records);//返回数据
    }


}

