package com.msr.car.controller;


import com.msr.car.entity.PostOrder;
import com.msr.car.service.PostOrderService;
import com.msr.common.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p >
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

}