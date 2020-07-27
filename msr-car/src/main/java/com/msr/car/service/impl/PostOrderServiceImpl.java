package com.msr.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.car.entity.PostOrder;
import com.msr.car.mapper.PostOrderMapper;
import com.msr.car.query.PostOrderQuery;
import com.msr.car.service.PostOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
@Service
public class PostOrderServiceImpl extends ServiceImpl<PostOrderMapper, PostOrder> implements PostOrderService {

    @Override
    public void pageQuery(Page<PostOrder> pageParam, PostOrderQuery postOrderQuery) {
        //条件构造器
        QueryWrapper<PostOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");

        if (postOrderQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String id = postOrderQuery.getId();


        if (!StringUtils.isEmpty(id)) {
            queryWrapper.eq("id", id);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }
}
