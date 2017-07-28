package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.ChatHis;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by M.c on 2016/7/15.
 */
public class HttpUtil {

//    public static void main(String[] args) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
//        String postBody = "Hello World";
//
//        Request request = new Request.Builder()
//                .url("http://www.baidu.com")
//                .post(RequestBody.create(MEDIA_TYPE_TEXT, postBody))
//                .build();
//
//        Response response = client.newCall(request).execute();
//        if (!response.isSuccessful()) {
//            throw new IOException("服务器端错误: " + response);
//        }
//
//        System.out.println(response.body().string());
//    }


    public static String post(String url,Object obj){
        String res = "";
        OkHttpClient client = new OkHttpClient();
        Map<String, Object> map = jsonUtil.json2Map(jsonUtil.toJson(obj));
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue().toString());
        }
        try {
            Response response = client.newCall(new Request.Builder().url(url).post(builder.build()).addHeader("Content-Type","application/json").build()).execute();
            System.out.println("返回"+response);
            res=response.body().string();
            System.out.println("请求地址: "+url+"\n请求参数: "+jsonUtil.toJson(obj)+"\n接口返回: "+res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static int postJson(String url,Object obj){
        String res="";
        int code=0;
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(obj)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
            code = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return 400;
        }
        return code;
    }

    public static String postToken(String url,Object obj){
        String res="";
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(obj)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
//            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return "token is null";
        }
        return res;
    }

    public static int deleteJson(String url,String token){
        String res="";
        int code=0;
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer "+token)
                .delete(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(null)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
            System.out.println(res);
            code = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return 400;
        }
        return code;
    }

    public static String chatgroups(String url,String token){
        String res="";
        int code=0;
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer "+token)
                .get()
//                .get(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(null)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
//            System.out.println(res);
            code = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return "res is null";
        }
        return res;
    }

    public static int addGroups(String url,String token,Object obj){
        String res="";
        int code=0;
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer "+token)
//                .get()
                .post(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(obj)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
//            System.out.println(res);
            code = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return 1005;
        }
        return code;
    }

    public static int deleteGroup(String url,String token){
        String res="";
        int code=0;
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer "+token)
                .delete(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(null)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
//            System.out.println(res);
            code = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return 400;
        }
        return code;
    }

    public static String getOneGroup(String url,String token){
        String res="";
        int code=0;
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer "+token)
                .get()
//                .get(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(null)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
//            System.out.println(res);
            code = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return "res is null";
        }
        return res;
    }

    public static int updateGroups(String url,String token,Object obj){
        String res="";
        int code=0;
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer "+token)
                .put(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(obj)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
//            System.out.println(res);
            code = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return 1005;
        }
        return code;
    }

    public static String groupMember(String url,String token){
        String res="";
        int code=0;
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer "+token)
                .get()
//                .get(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(null)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
//            System.out.println(res);
            code = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return "res is null";
        }
        return res;
    }

    public static int saveMember(String url,String token,Object obj){
        String res="";
        int code=0;
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer "+token)
//                .get()
                .post(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(obj)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
//            System.out.println(res);
            code = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return 1005;
        }
        return code;
    }

    public static int deleteMember(String url,String token){
        String res="";
        int code=0;
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer "+token)
                .delete(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(null)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
//            System.out.println(res);
            code = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return 400;
        }
        return code;
    }

    public static int addExcelUser(String url,String token,Object obj){
        String res="";
        int code=0;
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer "+token)
//                .get()
                .post(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(obj)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
//            System.out.println(res);
            code = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return 1005;
        }
        return code;
    }

    public static String chatHis(String url,String token){
        String res="";
        String targeturl="";
        int code = 0;
        Map<String,Object> map = new HashMap<String,Object>();
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer "+token)
                .get()
//                .get(RequestBody.create(MEDIA_TYPE_TEXT, jsonUtil.toJson(null)))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            code = response.code();
            if(code==200){
                res = response.body().string();
                map = jsonUtil.json2Map(res);
                JSONArray jsonArr = JSON.parseArray(JSON.toJSONString(map.get("data")));
                JSONObject jobj =  (JSONObject)jsonArr.get(0);
                targeturl = jobj.get("url").toString();
            }else{
                targeturl = "找不到记录";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targeturl;
    }

    public static int scorePost(String code,String txt){
        String res="";
        int codes=0;
        OkHttpClient client = new OkHttpClient();
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String O = MD5.string2MD5("QM"+ code + timeStamp);
        RequestBody formBody = new FormBody.Builder()
                .add("u",code)
                .add("t",timeStamp)
                .add("o",O)
                .add("v","activity")
                .add("txt","活动报名")
                .build();
        Request request = new Request.Builder()
                .url("http://www.shminde.com:9090/smly/resume/index.php/api/intrgral")
                .post(formBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
            System.out.println(res);
            codes = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(response.body().string());
        if(null==response){
            return 1005;
        }
        return codes;
    }
}
