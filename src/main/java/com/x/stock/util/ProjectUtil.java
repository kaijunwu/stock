package com.x.stock.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Title: ProjectUtil
 * ProjectName stock
 * Description:
 * author wukaijun
 * date 2020/9/30 15:15
 */
public class ProjectUtil {

    public static String getProjectRootPath(){
        try {
            Resource resource = new ClassPathResource("");
            return resource.getFile().getAbsolutePath();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }
}
