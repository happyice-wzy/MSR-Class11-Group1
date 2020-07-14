package com.msr.car.service;

import com.msr.car.entity.Model;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
public interface ModelService extends IService<Model> {
    public List<Model> getModel();
}
