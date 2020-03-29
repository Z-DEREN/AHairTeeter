package com.zdr.ahairteeter.demo.Tool.Particular;

import com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.Warehouse.SssGif;
import com.zdr.ahairteeter.demo.Tool.Crawler.pickpocket.Spiders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 该项目独有工具类
 */
public class PTool {
    private static final Logger logger = LogManager.getLogger(PTool.class.getName());

    @Autowired
    private static Spiders spiders;
    /**
     * 测试网络连接情况
     * @return
     */
    public  static  boolean NetBreak_make(String url , String ValidateParameter) {
        boolean TF = false;
        String text = spiders.spiders(url, 99999);
        int beginIndex = text.indexOf(ValidateParameter);
        if (beginIndex > 0) {
            TF = true;
            logger.info("[成功] 测试网络连接通过------------------------------------------------------------"); // info级别的信息
        }else {
            logger.info("[失败] 测试网络连接失败------------------------------------------------------------"); // info级别的信息
        }
        return TF;
    }







}
