package com.msr.car.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msr.car.entity.Store;
import com.msr.car.mapper.StoreMapper;
import com.msr.car.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {
    @Override
    public List<Store> listWithTree() {
        //1、查出所有分类
        List<Store> entities = baseMapper.selectList(null);
        //2、组装成父子的树形结构
        //2.1）、找到所有的一级分类
        List<Store> level1Menus = entities.stream().filter(Store ->
                Store.getParentId().equals("0")
        ).map((menu)->{
            menu.setChildren(getChildrens(menu,entities));
            return menu;
        }).collect(Collectors.toList());
        return level1Menus;
    }

    //递归查找所有菜单的子菜单
    private List<Store> getChildrens(Store root,List<Store> all){
        List<Store> children = all.stream().filter(Store -> {
            return Store.getParentId().equals(root.getId());
        }).map(Store -> {
            //1、找到子菜单
            Store.setChildren(getChildrens(Store,all));
            return Store;
        }).collect(Collectors.toList());
        return children;
    }

}

