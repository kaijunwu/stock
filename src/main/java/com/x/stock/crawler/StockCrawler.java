package com.x.stock.crawler;

import com.alibaba.fastjson.JSONObject;
import com.x.stock.bean.StockBase;
import com.x.stock.bean.StockInfo;
import com.x.stock.dao.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Title: StockCrawler
 * ProjectName stock
 * Description:
 * author wukaijun
 * date 2020/9/29 13:02
 */
public class StockCrawler {

    private final static Logger logger = LoggerFactory.getLogger(StockCrawler.class);

    private MessageMapper dao;

    private static StockCrawler stockCrawler;

    String url = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData";

    private int datalen = 4;

    public static void initStance(StockCrawler stockCrawler){
        StockCrawler.stockCrawler = stockCrawler;
    }

    public StockCrawler(MessageMapper dao){
        this.dao = dao;
    }

    public static StockCrawler getInstance(){
        return stockCrawler;
    }

    public void startStockCrawl(){
        logger.info("start crawl ... ");
        List<StockBase> stocks = StockCrawlerCode.getInstance().getAllStocks();
        logger.info("get stock base size : {}",stocks.size());

        logger.info("======================================================================================================");


        int num = 0;
        for(StockBase stock:stocks) {
            try {
                num ++;
                if(num > 100) break;
                Thread.sleep(1000);
                List<StockInfo> stockInfos = StockCrawlerDetail.getInstance().getStockInfos(url, stock.getCodeFull(), datalen);
                if (stockInfos == null) {
                    logger.error("stock detail crawl error : {}", stock);
                    continue;
                }
                logger.info("get stock info size : {}", stockInfos.size());
                stockInfos.forEach(e -> {
                    logger.info("save stock  : {}", e);
                    e.initBase(stock);
                    dao.saveStock(e);
                });
            } catch (Exception e) {
                logger.error("", e);
            }
        }



    }

}
