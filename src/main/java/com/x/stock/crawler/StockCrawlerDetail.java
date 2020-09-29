package com.x.stock.crawler;

import com.x.stock.bean.StockInfo;
import com.x.stock.util.MessagePullUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Title: MessagePullService
 * ProjectName demo
 * Description:
 * author wukaijun
 * date 2020/9/28 22:37
 */
@Repository
public class StockCrawlerDetail {

    private final static Logger logger = LoggerFactory.getLogger(StockCrawlerDetail.class);

    private static StockCrawlerDetail stockCrawlerDetail = new StockCrawlerDetail();

    public static StockCrawlerDetail getInstance(){
        return stockCrawlerDetail;
    }


    /**
     * @param symbol 代码
     * @param datalen 条数
     * @return
     */
    public List<StockInfo> getStockInfos(String url,String symbol,int datalen){
        return new MessagePullUtil(url,symbol,datalen).pullMessage();
    }

}
