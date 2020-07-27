package com.msr.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    /**
     * 根据分类名称查询这个一级分类中否存在
     * @param storeArea
     * @return
     */
    private Store getByStoreArea(String storeArea) {  // storeArea = 后端开发
        //条件构造器
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("store_area", storeArea); // storeArea = '后端开发'
        queryWrapper.eq("parent_id", "0");//一级分类  parent_id =0
        //sql = select * from edu_Store where storeArea = '后端开发' and parent_id = 0
        return baseMapper.selectOne(queryWrapper);//只会返回一条记录
    }

    /**
     * 根据分类名称和父id查询这个二级分类中否存在
     *
     * Store 即可以代表一级分类，又可以代表二级分类
     * @param storeArea
     * @return
     */
    private Store getSubByStoreArea(String storeArea, String parentId) { //java、1101348944920760321
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("store_area", storeArea);  //java
        queryWrapper.eq("parent_id", parentId);//1101348944920760321
        //sql = select * from edu where storeArea = 'java' and  parent_id = '1101348944920760321'
        return baseMapper.selectOne(queryWrapper);
    }

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

    @Override
    public void removeMenuByIds(List<Long> asList){
        System.out.println("------删除------:"+asList);
        baseMapper.deleteBatchIds(asList);
    }
}

