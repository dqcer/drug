package com.dqcer.drug.web.utils;

import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>Description:Map 转换工具类</p>
 * @author administrator
 * @date 2018/3/30 9:05
 * @param
 * @return
 */
public class MapUtil {

    /**
     * bean 转map
     * @param obj
     * @return
     */
    public static Map<String, Object> transBean2Map(Object obj) {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println("bean转换Map异常：" + e.getMessage());
        }
        return map;
    }

    /**
     * map 转 bean
     * @param map
     * @param obj
     */
    //map 转 bean
    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
        } catch (Exception e) {
            System.out.println("--->" + e);
        }
    }

    /**
     * list<map> 把所有map的key转换成驼峰形式
     */
    public static List<Map<String, Object>> truncListMap(List<Map<String, Object>> dataList) {
        List<Map<String, Object>> list = new ArrayList<>();
        for(Map<String, Object> map : dataList) {
            list.add(truncKey(map));
        }
        return list;
    }

    /**
     * list<map> 把所有map的key转换成驼峰形式
     */
    public static PageUtil<Map<String, Object>> truncPage(PageUtil<Map<String, Object>> pageUtil) {
        PageUtil<Map<String, Object>> page = new PageUtil<>();
        BeanUtils.copyProperties(pageUtil, page);
        page.setResult(truncListMap(pageUtil.getResult()));
        return page;
    }

    /**
     * 把map的key转换成驼峰
     * @param map
     */
    public static Map<String, Object> truncKey(Map<String, Object> map) {
        Map<String, Object> newMap = new TreeMap<>();
        String tmp;
        for(String field : map.keySet()) {
            if(field.equalsIgnoreCase("rn")) continue;
            tmp = truncStr(field, "_");
            newMap.put(tmp, map.get(field));
        }
        return newMap;
    }

    /**
     * 字符串 拆分
     * @param str
     */
    public static String truncStr(String str, String strPartt) {
        if(str.indexOf(strPartt) == -1) return str.toLowerCase();
        String[] strs = str.split(strPartt);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<strs.length;i++) {
            if(i==0) sb.append(strs[0].toLowerCase());
            else sb.append(strs[i].substring(0,1).toUpperCase()+strs[i].substring(1).toLowerCase());
        }
        return sb.toString();
    }

    /**
     * 针对 PageUtil 的转换
     * @param object
     */
    public static void truncPageUtil(Object object) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                if(property.getName().equalsIgnoreCase("result")) {
                    Method getter = property.getReadMethod();
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> list = truncListMap((List<Map<String, Object>>) getter.invoke(object));
                    Method setter = property.getWriteMethod();
                    setter.invoke(object, list);
                }
            }
        } catch (Exception e) {
            System.out.println("PageUtil 转换listMap异常：" + e.getMessage());
        }
    }
}
