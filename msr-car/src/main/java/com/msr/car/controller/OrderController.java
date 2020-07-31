package com.msr.car.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.car.entity.Order;
import com.msr.car.query.OrderQuery;
import com.msr.car.service.OrderService;
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
@Api(description="订单管理")
@RestController
@RequestMapping("/car/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "所有订单列表")
    @GetMapping("/list")
    public R list(){
        List<Order> orderList =  orderService.list(null);
        return R.ok().data("list",orderList);
    }

    @ApiOperation(value = "根据ID数组删除订单")
    @DeleteMapping("/delete")
    public R removeById(
            @ApiParam(name = "ids", value = "订单ID数组", required = true)
            @RequestBody String[] ids){
        orderService.removeByIds(Arrays.asList(ids));
        System.out.println("ids:" + ids);
        return R.ok();
    }

    @ApiOperation(value = "新增订单")
    @PostMapping("/save")
    public R save(
        @ApiParam(name = "order", value = "订单对象", required = true)
        @RequestBody Order order){
        orderService.save(order);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询订单")
    @GetMapping("/info/{id}")
    public R getById(
            @ApiParam(name = "id", value = "订单ID", required = true)
            @PathVariable String id){

        Order order = orderService.getById(id);
        return R.ok().data("item", order);
    }

    @ApiOperation(value = "根据ID修改订单")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(name = "order", value = "订单对象", required = true)
            @RequestBody Order order){
        orderService.updateById(order);
        return R.ok();
    }

    @ApiOperation(value = "分页订单列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "orderQuery", value = "查询对象", required = false)
            OrderQuery orderQuery,

            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        if(page <= 0 || limit <= 0){
            //throw new MSRException(21003, "参数不正确1");
            throw new MSRException(ResultCodeEnum.PARAM_ERROR);
        }

        Page<Order> pageParam = new Page<>(page, limit);
        orderService.pageQuery(pageParam,orderQuery);
        List<Order> records = pageParam.getRecords();//当前页的集合数据
        long total = pageParam.getTotal();   //总记录数
        return  R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "统计表")
    @GetMapping("/show-chart/{begin}/{end}")
    public R showChart(@PathVariable String begin,@PathVariable String end){
        Map<String, List> map = orderService.getChartData(begin, end);
        return  R.ok().data("map",map);
    }
}

