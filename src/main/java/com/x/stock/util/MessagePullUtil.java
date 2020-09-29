package com.x.stock.util;

import com.alibaba.fastjson.JSONArray;
import com.x.stock.bean.StockInfo;
import com.x.stock.crawler.StockCrawlerCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: MessagePullUtil
 * ProjectName demo
 * Description:
 * author wukaijun
 * date 2020/9/28 20:01
 */
public class MessagePullUtil {

    private final static Logger logger = LoggerFactory.getLogger(MessagePullUtil.class);

    private String serviceUrl = "";

    private String symbol;
    /**
     * 间隔  5 10 15 30 60
     * 默认  60
     */
    private int scale = 60;
    /**
     * 均值 5 10 15 30 60
     * 默认 5
     */
    private int ma = 5;
    /**
     * 获取的长度
     * 默认一天 4
     * 初次3个月 4 * 30 * 3 = 360
     */
    private int datalen = 4;

    public MessagePullUtil(){

    }

    public MessagePullUtil(String url, String symbol,int datalen) {
        this.symbol = symbol;
        this.datalen = datalen;
        this.serviceUrl = url;
    }

    public List<StockInfo> pullMessage() {
        String reponse = HttpClientUtil.doGet(serviceUrl, getMapParam());

        logger.info("==================================  {}" , reponse);

        return JSONArray.parseArray(reponse, StockInfo.class);
    }

    public Map<String, String> getMapParam() {
        Map<String,String> result = new HashMap();
        result.put("symbol", symbol);
        result.put("scale", String.valueOf(scale));
        result.put("ma", String.valueOf(ma));
        result.put("datalen", String.valueOf(datalen));
        return result;
    }


}
