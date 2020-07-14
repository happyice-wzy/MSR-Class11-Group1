package com.msr.car.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msr.car.entity.Model;
import com.msr.car.mapper.ModelMapper;
import com.msr.car.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Model> getModel() {
        return modelMapper.getModel();
    }
}
