package util;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/23.
 */

/**
 * JSON转换工具类 使用前调用getInstance方法
 *
 * @author M.c
 * @date 2014-11-13
 * @toJson 将对象转换成json字符串
 * @getValue 在json字符串中，根据key值找到value
 * @json2Map 将json格式转换成map对象
 * @json2Bean 将json转换成bean对象
 * @json2List 将json格式转换成List对象
 * @Obj2Map obj 转为 map
 */
public class jsonUtil {


    /**
     * 将对象转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return "{}";
        }
        return JSON.toJSONString(obj);
    }

    /**
     * 在json字符串中，根据key值找到value
     *
     * @param json
     * @param key
     * @return
     */
    public static Object getValue(String json, String key) {
        Object rulsObj = null;
        Map<?, ?> rulsMap = json2Map(json);
        if (rulsMap != null && rulsMap.size() > 0) {
            rulsObj = rulsMap.get(key);
        }
        return rulsObj;
    }

    /**
     * 将json格式转换成map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> json2Map(String json) {
        Map<String, Object> objMap = null;
        objMap = (Map<String, Object>) JSON.parse(json);
        if (objMap == null) {
            return null;
        }
        return objMap;
    }

    /**
     * 将json转换成bean对象
     *
     * @param <T>
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T json2Bean(String json, Class<T> clazz) {
        T obj = null;
        obj = JSON.parseObject(json, clazz);
        return obj;
    }

    /**
     * 将json格式转换成List对象
     *
     * @param json
     * @return
     */
    public static <T> List<T> json2List(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }


    /**
     * obj 转为 map
     *
     * @param obj 需要转的对象
     * @return
     */
    public static Map<String, Object> Obj2Map(Object obj) {
        if (obj != null) {
            return json2Map(toJson(obj));
        }
        return null;
    }


    /**
     * 返回对象
     * @param dataObj
     * @return
     */
//    public static String obj(Object dataObj) {
//
//        ResponseEntity res = new ResponseEntity();
//        res.setData(dataObj);
//        return toJson(res);
//
//    }


    /**
     * 返回 list
     * @param dataObj
     * @return
     */
//    public static String list(Object dataObj) {
//
//        ResponseEntity res = new ResponseEntity();
//        ResponseListEntity list = new ResponseListEntity();
//        list.setList(dataObj);
//        res.setData(list);
//        return toJson(res);
//
//    }

//    public static String list(int status, String info, Object dataObj) {
//
//        ResponseEntity res = new ResponseEntity();
//        ResponseListEntity list = new ResponseListEntity();
//        list.setList(dataObj);
//        res.setData(list);
//        res.setInfo(info);
//        res.setStatus(status);
//        return toJson(res);
//
//    }


}
