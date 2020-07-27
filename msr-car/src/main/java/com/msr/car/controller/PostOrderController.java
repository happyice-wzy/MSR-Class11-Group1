package com.msr.car.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.msr.car.entity.PostOrder;
import com.msr.car.query.PostOrderQuery;
import com.msr.car.service.PostOrderService;
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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
@Api(description="续租管理")
@RestController
@RequestMapping("/car/post-order")
public class PostOrderController {

    @Autowired
    private PostOrderService postOrderService;

    @ApiOperation(value = "续租")
    @PostMapping("/save")
    public R save(
            @ApiParam(name = "postOrder", value = "续租", required = true)
            @RequestBody PostOrder postOrder){  //传递的是json对象，@RequestBody把json对象转换为java对象
        postOrderService.save(postOrder);
        return R.ok();
    }

    @ApiOperation(value = "所有续租订单列表")
    @GetMapping("/list")
    public R list(){
        List<PostOrder> postOrderList =  postOrderService.list(null);
        return R.ok().data("list",postOrderList);
    }

    @ApiOperation(value = "根据ID数组删除续租订单")
    @DeleteMapping("/delete")
    public R removeById(
            @ApiParam(name = "ids", value = "续租订单ID数组", required = true)
            @RequestBody String[] ids){
        postOrderService.removeByIds(Arrays.asList(ids));
        System.out.println("ids:" + ids);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询续租订单")
    @GetMapping("/info/{id}")
    public R getById(
            @ApiParam(name = "id", value = "续租订单ID", required = true)
            @PathVariable String id){

        PostOrder postOrder = postOrderService.getById(id);
        return R.ok().data("item", postOrder);
    }

    @ApiOperation(value = "根据ID修改续租订单")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(name = "order", value = "订单对象", required = true)
            @RequestBody PostOrder postOrder){
        postOrderService.updateById(postOrder);
        return R.ok();
    }

    @ApiOperation(value = "分页续租订单列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "orderQuery", value = "查询对象", required = false)
                    PostOrderQuery postOrderQuery,

            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        if(page <= 0 || limit <= 0){
            //throw new MSRException(21003, "参数不正确1");
            throw new MSRException(ResultCodeEnum.PARAM_ERROR);
        }

        Page<PostOrder> pageParam = new Page<>(page, limit);
        //teacherService.page(pageParam, null);
        postOrderService.pageQuery(pageParam,postOrderQuery);
        List<PostOrder> records = pageParam.getRecords();//当前页的集合数据
        long total = pageParam.getTotal();   //总记录数
        return  R.ok().data("total", total).data("rows", records);
    }

}

