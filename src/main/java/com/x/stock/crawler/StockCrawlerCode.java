package com.x.stock.crawler;

import com.x.stock.bean.StockBase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Crawl stock code/name from FengHuang finance website:http://app.finance.ifeng.com/list/stock.php?t=hs
 * Main package:jsoup
 * Dependency:
 * <dependency>
 * <groupId>org.jsoup</groupId>
 * <artifactId>jsoup</artifactId>
 * <version>1.7.3</version>
 * </dependency>
 *
 * @author heyang
 */
@Repository
public class StockCrawlerCode {

    private final static Logger logger = LoggerFactory.getLogger(StockCrawlerCode.class);

    private static final String SRC_URL = "http://app.finance.ifeng.com/list/stock.php?t=hs";
    private static final String ENCODING = "utf-8";

    public static StockCrawlerCode getInstance() {
        return new StockCrawlerCode();
    }

    /**
     * 获取基本信息
     * @return
     */
    public List<StockBase> getAllStocks() {
        List<StockBase> result = new ArrayList<>();
        String url = SRC_URL;
        int idx = 0;
        while (true) {
            try {
                logger.info("--> start crawl content from {}", url);
                String html = getUrlHtml(url, ENCODING);
                //Thread.sleep(200);
                Document doc = Jsoup.parse(html, ENCODING);
                // Find core node
                Element divtab01 = doc.getElementsByClass("tab01").last();
                // Find stocks
                if(divtab01 == null) continue;
                Elements trs = divtab01.getElementsByTag("tr");
                for (Element tr : trs) {
                    Elements tds = tr.getElementsByTag("td");
                    if (tds.size() > 2) {
                        String code = tds.get(0).getElementsByTag("a").last().text();
                        String name = tds.get(1).getElementsByTag("a").last().text();
                        String codeFull = code;
                        if (code.startsWith("6")) {
                            codeFull = "sh" + code;
                        } else {
                            codeFull = "sz" + code;
                        }
                        StockBase stock = new StockBase(idx++, code, codeFull, name);
                        result.add(stock);
                    }
                }
                // Find next page url
                Element lastLink = divtab01.getElementsByTag("a").last();
                if (lastLink.text().equals("下一页")) {
                    url = "http://app.finance.ifeng.com/list/stock.php" + lastLink.attr("href");
                } else {
                    logger.info("crawl finish");
                    break;
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        System.out.println("共找到" + idx + "个股票.");
        return result;
    }


    /**
     * 获取Html
     * @param url
     * @param encoding
     * @return
     */
    private String getUrlHtml(String url, String encoding) {
        StringBuffer sb = new StringBuffer();
        URL urlObj = null;
        URLConnection openConnection = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            urlObj = new URL(url);
            openConnection = urlObj.openConnection();
            isr = new InputStreamReader(openConnection.getInputStream(), encoding);
            br = new BufferedReader(isr);
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sb.append(temp + "\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new StockCrawlerCode().getAllStocks();
    }
}