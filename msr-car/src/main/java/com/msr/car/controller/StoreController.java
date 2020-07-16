package com.msr.car.controller;


import com.msr.car.entity.Store;
import com.msr.car.service.StoreService;
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
@Api(description="门店管理")
@RestController
@RequestMapping("/car/store")
public class StoreController {
    //读取所有的门店信息
    //定义业务层对象
    @Autowired
    private StoreService storeService;

    @ApiOperation(value = "以树形结构显示所有门店分类")
    @GetMapping("/list/tree")
    public R list(){
        List<Store> listTree = storeService.listWithTree();
        return R.ok().data("data", listTree);
    }


    @ApiOperation(value = "根据ID删除门店")
    @DeleteMapping("/delete")
    public R removeById(
            @ApiParam(name = "ids", value = "门店ID", required = true)
            @RequestBody Long[] ids){
        //ids数组参数   :把数组类型转换为集合类型     Arrays.asList(数组) = 集合
        //removeByIds(集合参数)
        boolean flag =  storeService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }



    @ApiOperation(value = "新增店面")
    @PostMapping("/save")
    public R save(
            @ApiParam(name = "store", value = "店面对象", required = true)
            @RequestBody Store store){  //传递的是json对象，@RequestBody把json对象转换为java对象
        storeService.save(store);
        return R.ok();
    }

    //后端返回的数据如果是集合，则键的名称用list，如果返回的是对象统一使用item

    @ApiOperation(value = "根据ID查询店面")
    @GetMapping("/info/{id}") //路径传参
    public R getById(
            @ApiParam(name = "id", value = "店面ID", required = true)
            @PathVariable String id){

        Store store = storeService.getById(id);
        return R.ok().data("item", store);
    }

    @ApiOperation(value = "根据ID修改门店")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(name = "store", value = "门店对象", required = true)
            @RequestBody Store store){
        storeService.updateById(store);  // update  store  set ...   where id = ?
        return R.ok();
    }


}

