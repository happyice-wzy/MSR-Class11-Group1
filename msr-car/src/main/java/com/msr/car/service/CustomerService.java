package com.msr.car.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.car.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msr.car.query.CustomerQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tom
 * @since 2020-07-14
 */
public interface CustomerService extends IService<Customer> {
    void pageQuery(Page<Customer> pageParam, CustomerQuery teacherQuery);
    //MultipartFile ：图片，视频，音频，文档等等。二进制的数据07/26
    List<String> batchImport(MultipartFile file) throws Exception;

    //07/27 数据统计

//    Map<String, Object> getChartData(String begin, String end, String type);
    //07/28数据统计
    Map<String, List> getChartData(String begin, String end);

    Map<String, List> getEChartData();


}
