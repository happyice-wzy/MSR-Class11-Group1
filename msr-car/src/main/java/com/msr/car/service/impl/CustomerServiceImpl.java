package com.msr.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msr.car.entity.Customer;
import com.msr.car.mapper.CustomerMapper;
import com.msr.car.query.CustomerQuery;
import com.msr.car.service.CustomerService;
import com.msr.common.util.ExcelImportUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tom
 * @since 2020-07-14
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;
    @Override
    public void pageQuery(Page<Customer> pageParam, CustomerQuery customerQuery) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        if(customerQuery == null){
            baseMapper.selectPage(pageParam,queryWrapper);
            return;
        }
        String customerName = customerQuery.getCustomerName();

        String begin = customerQuery.getBegin();
        String end = customerQuery.getEnd();
        if(!StringUtils.isEmpty(customerName)){
            queryWrapper.like("customer_name",customerName);
        }

        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }
        baseMapper.selectPage(pageParam,queryWrapper);
    }
    //获取Excel记录并逐条导入 07/26
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
                    if (com.alibaba.nacos.client.utils.StringUtils.isEmpty(levelOneValue)) {
                        errorMsg.add("第" + rowNum + "行一级分类为空");
                        continue;
                    }
                }

                //判断一级分类是否重复 TODO
                //判断一级分类是否重复
//                Subject subject = this.getByTitle(levelOneValue);
//                String parentId = null;
//                if(subject == null){
//                    //将一级分类存入数据库
//                    Subject subjectLevelOne = new Subject();
//                    subjectLevelOne.setTitle(levelOneValue);
//                    subjectLevelOne.setSort(rowNum);
//                    baseMapper.insert(subjectLevelOne);//添加
//                    parentId = subjectLevelOne.getId();
//                }else{
//                    parentId = subject.getId();
//                }
                //把标题传递到验证的方法中，而且返回对象
                //如果返回的对象是null值，则说明数据库不存在此对象
                //如果返回的对象不是null值，则说明数据库存在了此对象



                //获取二级分类
                String levelTwoValue = "";
                Cell levelTwoCell = rowData.getCell(1);
                if(levelTwoCell != null){
                    levelTwoValue = excelHSSFUtil.getCellValue(levelTwoCell).trim();
                    if (com.alibaba.nacos.client.utils.StringUtils.isEmpty(levelTwoValue)) {
                        errorMsg.add("第" + rowNum + "行二级分类为空");
                        continue;
                    }
                }

//判断二级分类是否重复
//                Subject subjectSub = this.getSubByTitle(levelTwoValue, parentId);
//                Subject subjectLevelTwo = null;
//                if(subjectSub == null){
//                    //将二级分类存入数据库
//                    subjectLevelTwo = new Subject();
//                    subjectLevelTwo.setTitle(levelTwoValue);
//                    subjectLevelTwo.setParentId(parentId);
//                    subjectLevelTwo.setSort(rowNum);
//                    baseMapper.insert(subjectLevelTwo);//添加
//                    parentId = subjectLevelTwo.getId();
//                }else{
//                    parentId = subjectSub.getId();
//                }


                //获取三级分类
                String levelThreeValue = "";
                Cell levelThreeCell = rowData.getCell(2);
                if(levelThreeCell != null){
                    levelThreeValue = excelHSSFUtil.getCellValue(levelThreeCell).trim();
                    if (com.alibaba.nacos.client.utils.StringUtils.isEmpty(levelThreeValue)) {
                        errorMsg.add("第" + rowNum + "行三级分类为空");
                        continue;
                    }
                }

                //判断三级分类是否重复
//                Subject subjectThird = this.getSubByTitle(levelThreeValue, parentId);
//                Subject subjectLevelThree = null;
//                if(subjectThird == null){
//                    //将三级分类存入数据库
//                    subjectLevelThree = new Subject();
//                    subjectLevelThree.setTitle(levelThreeValue);
//                    subjectLevelThree.setParentId(parentId);
//                    subjectLevelThree.setSort(rowNum);
//                    baseMapper.insert(subjectLevelThree);//添加
//                }

            }
        }

        return errorMsg;
    }




    /**
     * 根据分类名称查询这个一级分类中否存在
     * @param title
     * @return
     */
//    private Subject getByTitle(String title) {
//
//        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("title", title);
//        queryWrapper.eq("parent_id", "0");//一级分类
//        return baseMapper.selectOne(queryWrapper);
//    }

    /**
     * 根据分类名称和父id查询这个二级分类中否存在
     * @param title
     * @return
     */
//    private Subject getSubByTitle(String title, String parentId) {
//
//        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("title", title);
//        queryWrapper.eq("parent_id", parentId);
//        return baseMapper.selectOne(queryWrapper);
//    }
    /**
     * 根据分类名称和父id查询这个二级分类中否存在
     * @param
     * @return
     */
//    private Subject getThirdByTitle(String title, String parentId) {
//
//        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("title", title);
//        queryWrapper.eq("parent_id", parentId);
//        return baseMapper.selectOne(queryWrapper);
//    }


//    @Override
//    public Map<String, Object> getChartData(String begin, String end, String type) {
//
//        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
//        customerQueryWrapper.select(type, "gmt_create");
//        customerQueryWrapper.between("gmt_create", begin, end);
//
//        List<Customer> customerList = baseMapper.selectList(customerQueryWrapper);
//
//        Map<String, Object> map = new HashMap<>();
//        List<String> dataList = new ArrayList<String>();
//        List<Date> dateList = new ArrayList<Date>();
//        map.put("dataList", dataList);
//        map.put("dateList", dateList);
//
//
//        for (int i = 0; i < customerList.size(); i++) {
//            Customer customer = customerList.get(i);
//            dateList.add(customer.getGmtCreate());
//            switch (type) {
//                case "id":
//                    dataList.add(customer.getId());
//                    break;
//
//                default:
//                    break;
//            }
//        }
//
//        return map;
//    }

    @Override
    public Map<String, List> getChartData(String begin, String end) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, List> map = new HashMap<>();
        try {
            Date dateBegin = simpleDateFormat.parse(begin);
            Date dateEnd = simpleDateFormat.parse(end);
            List<Customer>  list = customerMapper.getChartData(dateBegin , dateEnd);
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
    @Override
    public Map<String, List> getEChartData() {

        Map<String, List> map = new HashMap<>();


        List<Customer>  list = customerMapper.getEChartData();
        List id = new ArrayList();
        List gender = new ArrayList();
        for(int i = 0 ;i < list.size();i++){
            id.add(list.get(i).getId());
            gender.add(list.get(i).getCustomerSex());

            map.put("id",id);
            map.put("gender" , gender);
        }




        return map;
    }
}
