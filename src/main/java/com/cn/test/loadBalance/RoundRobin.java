package com.cn.test.loadBalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoundRobin {
	
	private static Integer pos = 0; 
	public static String getServer() {
		// 重建一个Map，避免服务器的上下线导致的并发问题   
        Map<String, Integer> serverMap =    
                new HashMap<String, Integer>();   
        serverMap.putAll(IpMap.serverWeightMap);  
		
     // 取得Ip地址List 
        /**
         * map集合中的两种取出方式： 
 			1、Set<k> keySet：将map集合中所有的键存入到Set集合，因为Set具备迭代器。 
            	所有可用迭代方式取出所有的键，再根据get方法，获取每一个键对应的值。 
            	Map集合的取出原理：将Map集合转成Set集合，再通过迭代器取出。 
 			2、 Set<Map.Entry<K, V>> entrySet:将Map集合中的映射关系存入到Set集合中，二这个关系的数据类型就是Map.entry。 
    			Map.Entry  其实Entry也是一个接口，它是Map接口中的一个内部接口。 
    		3，makes inefficient use of keySet iterator instead of entrySet iterator 
    			keySet 方式遍历Map的性能不如entrySet性能好
         */
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<>(keySet);
        
        String server = null;   
        synchronized (pos)   
        {   
            if (pos > keySet.size())   
                pos = 0;   
            server = keyList.get(pos);  
            pos ++;   
        }   

        return server;
	}
	
	public static void main(String[] args) {
		getServer();
	}
}
