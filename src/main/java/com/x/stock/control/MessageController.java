package com.x.stock.control;

import com.x.stock.bean.StockInfo;
import com.x.stock.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 * Title: MessageController
 * ProjectName demo
 * Description:
 * author wukaijun
 * date 2020/9/28 16:31
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    private final static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    MessageService service;

    @RequestMapping(value = "getMessage", method = RequestMethod.GET)
    public List<StockInfo> findMessage(){
        //service.saveMessage("{\"day\":\"2020-09-28 14:20:00\",\"open\":\"15.300\",\"high\":\"15.310\",\"low\":\"15.270\",\"close\":\"15.280\",\"volume\":\"516911\",\"ma_price5\":15.294,\"ma_volume5\":807738}");
        return service.getMessage();
    }

    @RequestMapping(value = "getMaNum", method = RequestMethod.GET)
    public String getMaNum(){
        StringBuilder builder = new StringBuilder();
        List<Map<String,Object>> list = service.getMaNum();
        for(Map<String,Object> map:list){
            builder.append(map);
        }
        return builder.toString();
    }
}
