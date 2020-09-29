package com.x.stock.service;

import com.x.stock.bean.StockInfo;
import com.x.stock.crawler.StockCrawler;
import com.x.stock.dao.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Title: MessageService
 * ProjectName demo
 * Description:
 * author wukaijun
 * date 2020/9/28 15:15
 */
@Service
@Repository
public class MessageService implements InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Resource
    private MessageMapper dao;


    /**
     * 读取
     * @return
     */
    public List<StockInfo> getMessage(){
        return dao.getMessage();
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                /** 初始化对象 **/
                StockCrawler stockCrawler = new StockCrawler(dao);
                StockCrawler.initStance(stockCrawler);
                /** 提交调度 **/
        /*JobManager.getInstance().addJob(  //0/2 * * * * ?
                "stockCrawl",
                "stockCrawl",
                "stockCrawl",
                "stockCrawl",
                StockCrawlJob.class,
                "0/10 * * * * ?",   //  0/2 * * * * ?  0 0/2 * * * ?
                new HashMap<>()
        );*/

                /** 提交调度 **/
                //StockCrawler.getInstance().startStockCrawl();
            }
        });
        thread.setName("stock-info-crawl");
        thread.setDaemon(true);
        thread.start();
    }

    public List<Map<String,Object>> getMaNum() {
        return dao.getMaNum();
    }
}
