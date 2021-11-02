package com.changlu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @ClassName Crawler
 * @Author ChangLu
 * @Date 2021/10/30 22:43
 * @Description TODO
 */
public class Crawler {

    public static Logger logger = Logger.getLogger("com.changlu");

    public static void main(String[] args) throws IOException {
        logger.info("进行请求中...");
        Document document = Jsoup.connect("https://github.com/freefq/free").get();
        logger.info("开始解析...");
        String content  = document.getElementsByTag("code").eq(2).text();
        logger.info("解析成功...");
        writeFile(content);
    }

    public static void writeFile(String content){
        BufferedOutputStream bos = null;
        try {
            logger.info("写入文件中....");
            bos = new BufferedOutputStream(new FileOutputStream("code.txt"));
            bos.write(content.getBytes());
            logger.info("写入成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}