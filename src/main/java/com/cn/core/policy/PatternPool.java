package com.cn.core.policy;

import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

public class PatternPool {


    public PatternPool()
    {
    }

    public static Pattern getPattern(String s)
    {
        Pattern pattern = (Pattern)a.get(s);
        if(pattern == null)
        {
            pattern = Pattern.compile(s);
            a.put(s, pattern);
        }
        return pattern;
    }

    private static Map a = new Hashtable();


}
