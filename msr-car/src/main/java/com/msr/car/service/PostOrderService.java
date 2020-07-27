package com.msr.car.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.car.entity.PostOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msr.car.query.PostOrderQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
public interface PostOrderService extends IService<PostOrder> {
    void pageQuery(Page<PostOrder> pageParam, PostOrderQuery postOrderQuery);
}
