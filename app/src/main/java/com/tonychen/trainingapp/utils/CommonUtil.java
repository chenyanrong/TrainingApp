package com.tonychen.trainingapp.utils;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by TonyChen on 2017/08/04;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public class CommonUtil {
    private CommonUtil() {
    }

    public static void LogInfo(Object object) {
        if (object instanceof Map) {
            Map map = (Map) object;
            com.orhanobut.logger.Logger.i("数据类型是Map 长度 = " + map.size());
            Set<String> keys = map.keySet();
            for (String key : keys) {
                com.orhanobut.logger.Logger.i("key = " + key + " value = " + map.get(key));
            }
        }
    }
}
