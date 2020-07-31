package com.msr.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msr.car.entity.Store;
import com.msr.car.mapper.StoreMapper;
import com.msr.car.service.StoreService;
import com.msr.common.util.ExcelImportUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
    @Autowired
    StoreMapper storeMapper;
    /**
     * 根据分类名称查询这个一级分类中否存在
     *
     * @param storeArea
     * @return
     */
    private Store getByStoreArea(String storeArea) {  // storeArea
        //条件构造器
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("store_area", storeArea); // storeArea
        queryWrapper.eq("parent_id", "0");//一级分类  parent_id =0
        //sql = select * from edu_Store where storeArea  and parent_id = 0
        return baseMapper.selectOne(queryWrapper);//只会返回一条记录
    }

    /**
     * 根据分类名称和父id查询这个二级分类中否存在
     * <p>
     * Store 即可以代表一级分类，又可以代表二级分类
     *
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
        ).map((menu) -> {
            menu.setChildren(getChildrens(menu, entities));
            return menu;
        }).collect(Collectors.toList());
        return level1Menus;
    }

    //递归查找所有菜单的子菜单
    private List<Store> getChildrens(Store root, List<Store> all) {
        List<Store> children = all.stream().filter(Store -> {
            return Store.getParentId().equals(root.getId());
        }).map(Store -> {
            //1、找到子菜单
            Store.setChildren(getChildrens(Store, all));
            return Store;
        }).collect(Collectors.toList());
        return children;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        System.out.println("------删除------:" + asList);
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public List<String> batchImport(MultipartFile file) throws Exception {

        //错误消息列表
        List<String> errorMsg = new ArrayList<>();

        //创建工具类对象
        ExcelImportUtil excelHSSFUtil = new ExcelImportUtil(file.getInputStream());
        //获取工作表
        Sheet sheet = excelHSSFUtil.getSheet();

        int rowCount = sheet.getPhysicalNumberOfRows();
        if (rowCount <= 1) {
            errorMsg.add("请填写数据");
            return errorMsg;
        }

        for (int rowNum = 1; rowNum < rowCount; rowNum++) {

            Row rowData = sheet.getRow(rowNum);
            if (rowData != null) {// 行不为空

                //获取一级分类
                String levelOneValue = "";
                Cell levelOneCell = rowData.getCell(0);
                if (levelOneCell != null) {
                    levelOneValue = excelHSSFUtil.getCellValue(levelOneCell).trim();
                    if (StringUtils.isEmpty(levelOneValue)) {
                        errorMsg.add("第" + rowNum + "行一级分类为空");
                        continue;
                    }
                }

                //判断一级分类是否重复
                //将一级分类存入数据库
                Store store = this.getByStoreArea(levelOneValue);//
                String parentId = null;
                if (store == null) {
                    //将一级分类存入数据库
                    Store storeLevelOne = new Store();
                    storeLevelOne.setStoreArea(levelOneValue);
//                    storeLevelOne.setSort(rowNum);
                    baseMapper.insert(storeLevelOne);//添加
                    parentId = storeLevelOne.getId();
                } else {
                    parentId = store.getId();
                }

                //获取二级分类
                String levelTwoValue = "";
                Cell levelTwoCell = rowData.getCell(1);
                if (levelTwoCell != null) {
                    levelTwoValue = excelHSSFUtil.getCellValue(levelTwoCell).trim();
                    if (StringUtils.isEmpty(levelTwoValue)) {
                        errorMsg.add("第" + rowNum + "行二级分类为空");
                        continue;
                    }
                }
                //判断二级分类是否重复
                Store storeSub = this.getSubByStoreArea(levelTwoValue, parentId);
                Store storeLevelTwo = null;
                if (storeSub == null) {
                    //将二级分类存入数据库
                    storeLevelTwo = new Store();
                    storeLevelTwo.setStoreArea(levelTwoValue);
                    storeLevelTwo.setParentId(parentId);
//                    storeLevelTwo.setSort(rowNum);
                    baseMapper.insert(storeLevelTwo);//添加
                }
            }
        }
        return errorMsg;
    }

    @Override
    public Map<String, List> getChartData(String begin, String end) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, List> map = new HashMap<>();
        try {
            Date dateBegin = simpleDateFormat.parse(begin);
            Date dateEnd = simpleDateFormat.parse(end);
            List<Store>  list = storeMapper.getChartData(dateBegin , dateEnd);
            List id = new ArrayList();
            List date = new ArrayList();
            for(int i = 0 ;i < list.size();i++){
                id.add(list.get(i).getId());
                date.add(list.get(i).getGmtCreate());
                map.put("id",id);
                map.put("date" , date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }
}

