package com.cn.common.util;

import java.util.List;
import java.util.Map;



/**
 * @description 工具类，包含一些常用的工具方法
 */
public class Util {
	   /**
     * 判断一个Object是否为空 修改为支持List ,Map ,String
     * @param obj
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNullOrEmpty(Object obj) {
        if (obj instanceof Object[]) {
            Object[] o = (Object[]) obj;
            if (o == null || o.length == 0) {
                return true;
            }
            return false;
        } else {
            if (obj instanceof String) {
                if ((obj == null) || (("").equals(((String) obj).trim()))) {
                    return true;
                }
                return false;
            }
            if (obj instanceof List) {
                List objList = (List) obj;
                if (objList == null || objList.isEmpty()) {
                    return true;
                }
                return false;
            }
            if (obj instanceof Map) {
                Map objMap = (Map) obj;
                if (objMap == null || objMap.isEmpty()) {
                    return true;
                }
                return false;
            }
            if ((obj == null) || (("").equals(obj))) {
                return true;
            }
        }
        return false;
    }
}
