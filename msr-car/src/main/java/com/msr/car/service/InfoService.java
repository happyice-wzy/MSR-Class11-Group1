package com.msr.car.service;

import com.msr.car.entity.Info;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
public interface InfoService extends IService<Info> {
    Map<String, List> getChartData(String begin, String end);
}
