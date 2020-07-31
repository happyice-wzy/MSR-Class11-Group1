package com.msr.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msr.car.entity.Order;
import com.msr.car.mapper.OrderMapper;
import com.msr.car.query.OrderQuery;
import com.msr.car.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tom
 * @since 2020-07-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public void pageQuery(Page<Order> pageParam, OrderQuery orderQuery) {
        //条件构造器
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");

        if (orderQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String id = orderQuery.getId();

        if (!StringUtils.isEmpty(id)) {
            queryWrapper.eq("id", id);
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }

//    @Override
//    public Map<String, Object> getChartData(String begin, String end) {
//
//        QueryWrapper<Order> dayQueryWrapper = new QueryWrapper<>();
//
//        dayQueryWrapper.between("gmt_create", begin, end);
//
//        List<Order> orderList = baseMapper.selectList(dayQueryWrapper);
//
//        Map<String, Object> map = new HashMap<>();
//        List<String> dataList = new ArrayList<String>();
//        List<Date> dateList = new ArrayList<Date>();
//        map.put("dataList", dataList);
//        map.put("dateList", dateList);
//
//
//        for (int i = 0; i < orderList.size(); i++) {
//            Order order = orderList.get(i);
//            dateList.add(order.getGmtCreate());
//            switch (type) {
//                case "id":
//                    dataList.add(order.getId());
//                    break;
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
            List<Order>  list = orderMapper.getChartData(dateBegin , dateEnd);
            System.out.println(list);
            List id = new ArrayList();
            List date = new ArrayList();
            for(int i = 0 ;i < list.size();i++){
               id.add(list.get(i).getId());
                date .add(list.get(i).getGmtCreate());

               map.put("id",id);
               map.put("date" , date);
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return map;

    }

}
