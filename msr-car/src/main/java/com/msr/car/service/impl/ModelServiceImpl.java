package com.msr.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msr.car.entity.Model;
import com.msr.car.mapper.ModelMapper;
import com.msr.car.query.ModelQuery;
import com.msr.car.service.ModelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements ModelService {

    @Override
    public void pageQuery(Page<Model> pageParam, ModelQuery modelQuery) {
        //条件构造器
        QueryWrapper<Model> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");//字段而不是属性

        if (modelQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String modelBrand = modelQuery.getModelBrand();
        String begin = modelQuery.getBegin();
        String end = modelQuery.getEnd();

        if (!StringUtils.isEmpty(modelBrand)) {
            queryWrapper.like("model_brand", modelBrand);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }
}
