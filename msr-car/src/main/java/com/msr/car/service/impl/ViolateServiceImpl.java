package com.msr.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.car.entity.Violate;
import com.msr.car.mapper.ViolateMapper;
import com.msr.car.query.ViolateQuery;
import com.msr.car.service.ViolateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
@Service
public class ViolateServiceImpl extends ServiceImpl<ViolateMapper, Violate> implements ViolateService {
    @Override
    public void pageQuery(Page<Violate> pageParam, ViolateQuery violateQuery) {
        //条件构造器
        QueryWrapper<Violate> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");//字段而不是属性

        if (violateQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String id = violateQuery.getId();
        String begin = violateQuery.getBegin();
        String end = violateQuery.getEnd();

        if (!StringUtils.isEmpty(id)) {
            queryWrapper.like("id",id );
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
