package com.msr.car.service;
import com.msr.car.entity.Store;
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
public interface StoreService extends IService<Store> {
    List<Store> listWithTree();
}
