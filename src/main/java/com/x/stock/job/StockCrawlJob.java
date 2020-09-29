package com.x.stock.job;

import com.x.stock.crawler.StockCrawler;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title: StockCrawJob
 * ProjectName demo
 * Description:
 * author wukaijun
 * date 2020/9/29 0:20
 */
public class StockCrawlJob implements Job {

    private final static Logger logger = LoggerFactory.getLogger(StockCrawlJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("stock crawl Job start ... ");
        StockCrawler.getInstance().startStockCrawl();
    }

}
