package com.msr.car.service.impl;

import com.msr.car.entity.Info;
import com.msr.car.mapper.InfoMapper;
import com.msr.car.service.InfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {

}
