package util;

import model.PageParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/4.
 */
public class ReturnMap {
    public Map<String,Object> getMap(PageParam pageParam){
        Map<String,Object> map = new HashMap<>();
        int page = (pageParam.getCurrentPage()-1)*pageParam.getRow();
        map.put("page",page);
        map.put("row",pageParam.getRow());
        if(pageParam.getSearch()!=""){
            map.put("search",pageParam.getSearch());
        }
        return map;
    }
}
