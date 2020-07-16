package com.msr.car.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msr.car.entity.Model;
import com.msr.car.query.ModelQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
public interface ModelService extends IService<Model> {
    void pageQuery(Page<Model> pageParam, ModelQuery modelQuery);
}
