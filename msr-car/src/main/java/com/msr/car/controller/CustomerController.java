package com.msr.car.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.car.entity.Customer;
import com.msr.car.query.CustomerQuery;
import com.msr.car.service.CustomerService;
import com.msr.common.constants.ResultCodeEnum;
import com.msr.common.exception.MSRException;
import com.msr.common.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tom
 * @since 2020-07-14
 */
@Api(description="客户管理")
@RestController
@RequestMapping("/car/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "所有客户列表")
    @GetMapping("/list")
    public R list(){
        List<Customer> customerList = customerService.list(null);
        return R.ok().data("list",customerList);
    }
    @ApiOperation(value = "分页客户列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name="page", value = "当前页码", required = true)
            @PathVariable long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit,
            @ApiParam(name = "customerQuery",value = "查询对象",required = false)
                    CustomerQuery customerQuery){
        if (page<=0 || limit<=0){
            //throw new MSRException(21003,"参数不正确1");
            throw new MSRException(ResultCodeEnum.PARAM_ERROR);
        }
        Page<Customer> pageParam = new Page<>(page,limit);
        customerService.pageQuery(pageParam,customerQuery);
        List<Customer> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "根据ID删除客户")
    @DeleteMapping("/delete")
    public R removeById(
            @ApiParam(name = "ids", value = "客户ID数组", required = true)
            @RequestBody String[] ids){
        //ids数组参数: 把数组类型转换为集合类型 Arrays.asList(数组)=集合
        //removeByIds(集合参数)
        customerService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @ApiOperation(value = "新增客户")
    @PostMapping("/save")
    public R save(
            @ApiParam(name = "customer", value = "客户对象", required = true)
            @RequestBody Customer customer){
        customerService.save(customer);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询客户")
    @GetMapping("/info/{id}")
    public R getById(
            @ApiParam(name = "id", value = "客户id", required = true)
            @PathVariable String id){
        Customer customer = customerService.getById(id);
        return R.ok().data("item",customer);
    }

    @ApiOperation(value = "根据ID修改客户")
    @PutMapping("/update")
    public R updateById(
            //@ApiParam(name="id", value = "客户id",required = true)
            //@PathVariable String id,
            @ApiParam(name = "customer",value = "客户对象",required = true)
            @RequestBody Customer customer){
        //customer.setId(id);
        customerService.updateById(customer);
        return  R.ok();
    }
    //excel 导入07/27
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("import")
    public R batchImport(
            @ApiParam(name = "file", value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file) {

        try{
            List<String> errorMsg = customerService.batchImport(file);
            if(errorMsg.size() == 0){
                return R.ok().message("批量导入成功");
            }else{
                return R.error().message("部分数据导入失败").data("errorMsgList", errorMsg);
            }

        }catch (Exception e){
            //无论哪种异常，只要是在excel导入时发生的，一律用转成GuliException抛出
            throw new MSRException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }
    //07/28 echarts添加
    @ApiOperation(value = "统计表")
    @GetMapping("/show-chart/{begin}/{end}")
    public R showChart(@PathVariable String begin,@PathVariable String end){
        Map<String, List> map = customerService.getChartData(begin, end);
        return  R.ok().data("map",map);
    }
    @ApiOperation(value = "饼状图")
    @GetMapping("/show-EChart")
    public R showEChart() {
        Map<String, List> map = customerService.getEChartData();
        return R.ok().data("map", map);

    }
}

