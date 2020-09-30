package com.x.stock.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: HtmlUtilBuild
 * ProjectName stock
 * Description:
 * author wukaijun
 * date 2020/9/29 19:37
 */
public class HtmlUtilBuild {

    public static String buildHtml(List<Map<String,Object>> param) {
        String s1 = "<!DOCTYPE html>\n" +
                "<html style=\"height: 100%\">\n" +
                "   <head>\n" +
                "       <meta charset=\"utf-8\">\n" +
                "   </head>\n" +
                "   <body style=\"height: 100%; margin: 0\">\n" +
                "       <div id=\"container\" style=\"height: 100%\"></div>\n" +
                "       <script type=\"text/javascript\" src=\"https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js\"></script>\n" +
                "       <script type=\"text/javascript\" src=\"https://cdn.jsdelivr.net/npm/echarts-gl/dist/echarts-gl.min.js\"></script>\n" +
                "       <script type=\"text/javascript\" src=\"https://cdn.jsdelivr.net/npm/echarts-stat/dist/ecStat.min.js\"></script>\n" +
                "       <script type=\"text/javascript\" src=\"https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js\"></script>\n" +
                "       <script type=\"text/javascript\" src=\"https://cdn.jsdelivr.net/npm/echarts/map/js/china.js\"></script>\n" +
                "       <script type=\"text/javascript\" src=\"https://cdn.jsdelivr.net/npm/echarts/map/js/world.js\"></script>\n" +
                "       <script type=\"text/javascript\" src=\"https://api.map.baidu.com/api?v=2.0&ak=xfhhaTThl11qYVrqLZii6w8qE5ggnhrY&__ec_v__=20190126\"></script>\n" +
                "       <script type=\"text/javascript\" src=\"https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js\"></script>\n" +
                "       <script type=\"text/javascript\">\n" +
                "var dom = document.getElementById(\"container\");\n" +
                "var myChart = echarts.init(dom);\n" +
                "var app = {};\n" +
                "option = null;\n" +
                "option = {\n" +
                "    xAxis: {\n" +
                "        type: 'category',\n" +
                "        data: [";
        String s2 = "]\n" +
                "    },\n" +
                "    yAxis: {\n" +
                "        type: 'value'\n" +
                "    },\n" +
                "    series: [{\n" +
                "        data: [";

        String s3 = "],\n" +
                "        type: 'line',\n" +
                "        smooth: true\n" +
                "    }]\n" +
                "};\n" +
                ";\n" +
                "if (option && typeof option === \"object\") {\n" +
                "    myChart.setOption(option, true);\n" +
                "}\n" +
                "       </script>\n" +
                "   </body>\n" +
                "</html>";

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (Map<String, Object> map : param) {
            sb1.append("'").append(map.get("day").toString()).append("'").append(",");
            sb2.append("").append(map.get("count").toString()).append("").append(",");
        }
        String ss1 = sb1.substring(0, sb1.length()-1).toString();
        String ss2 = sb2.substring(0, sb2.length()-1).toString();
        return s1 + ss1 + s2 + ss2 + s3;

    }
    
    
    public static void main(String[] args){
        List<Map<String,Object>> param = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("count",9);
        map1.put("day","2020-09-29 11:30:00");

        Map<String,Object> map2 = new HashMap<>();
        map2.put("count",12);
        map2.put("day","2020-09-29 15:00:00");

        param.add(map1);
       param.add(map2);

        String ss = HtmlUtilBuild.buildHtml(param);
        System.out.println(ss);

    }
}
