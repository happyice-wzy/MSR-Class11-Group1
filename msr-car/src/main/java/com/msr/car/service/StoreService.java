package com.msr.car.service;
import com.msr.car.entity.Store;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

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
public interface StoreService extends IService<Store> {
    List<Store> listWithTree();
    //批量删除
    void removeMenuByIds(List<Long> asList);

   List<String> batchImport(MultipartFile file) throws Exception;

    Map<String, List> getChartData(String begin, String end);
}
