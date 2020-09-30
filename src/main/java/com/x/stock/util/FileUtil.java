package com.x.stock.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Title: FileUtil
 * ProjectName fetch
 * Description:
 * author wukaijun
 * date 2019/9/18 17:33
 */
public class FileUtil {

    /**
     * 文件内容读取为字符串
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static String getStringFromFile(File file) {
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream inputStream = new FileInputStream(file);
            int length = inputStream.available();
            byte bytes[] = new byte[length];
            inputStream.read(bytes);
            inputStream.close();
            String str = new String(bytes,"utf-8");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 把内容content写的path文件中
     *
     * @param content 输入内容
     * @param path 文件路径
     * @return
     */
    public static boolean save2File(String path,String content) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File(path), false);
            if (content != null) {
                fw.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fw != null) {
                try {
                    fw.flush();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }



}
