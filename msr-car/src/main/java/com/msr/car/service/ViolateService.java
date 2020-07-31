package com.msr.car.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msr.car.entity.Violate;
import com.msr.car.query.ViolateQuery;

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
public interface ViolateService extends IService<Violate> {
    void pageQuery(Page<Violate> pageParam, ViolateQuery violateQuery);
    Map<String, List> getChartData(String begin, String end);

}
