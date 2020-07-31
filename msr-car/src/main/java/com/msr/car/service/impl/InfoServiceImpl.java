package com.msr.car.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msr.car.entity.Info;
import com.msr.car.mapper.InfoMapper;
import com.msr.car.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {
    @Autowired
    InfoMapper infoMapper;
    @Override
    public Map<String, List> getChartData(String begin, String end) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, List> map = new HashMap<>();
        try {
            Date dateBegin = simpleDateFormat.parse(begin);
            Date dateEnd = simpleDateFormat.parse(end);
            List<Info>  list = infoMapper.getChartData(dateBegin , dateEnd);
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
