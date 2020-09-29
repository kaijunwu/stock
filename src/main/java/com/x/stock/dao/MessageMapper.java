package com.x.stock.dao;

import com.x.stock.bean.StockInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Title: Stock
 * ProjectName demo
 * Description:
 * author wukaijun
 * date 2020/9/27 18:10
 */
@Mapper
@Repository
public interface MessageMapper {

    void saveStock(StockInfo message);

    List<StockInfo> getMessage();

    List<Map<String,Object>> getMaNum();
}
