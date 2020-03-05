package com.wei.common.resttemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/4 0004
 */
public class Demo01 {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        //RestTemplate restTemplate = new RestTemplate();//这个也可以
        String url = "http://www.zgpjb.com/pj-p2p-activity/pcBanner/queryFriendlyLinks";

        method01(restTemplate,url);
        //method02(restTemplate,url);

    }

    public static void method01(RestTemplate restTemplate,String url){
        ResponseEntity<Map> forEntity = restTemplate.postForEntity(url, null,Map.class);
        Map body = forEntity.getBody();
        String data = JSON.toJSONString(body.get("data"));
        System.out.println(data);
        List<FriendlyLink> list = JSONObject.parseArray(data, FriendlyLink.class);

        for (FriendlyLink friendlyLink : list) {
            System.out.println(friendlyLink);
        }
        System.out.println("~~请求结束~~");
    }

    //目前有点问题
    public static void method02(RestTemplate restTemplate,String url){
        ApiResult result = restTemplate.postForObject(url, null, ApiResult.class);
        System.out.println(result);
        List<FriendlyLink> list = (List<FriendlyLink>)result.getData();
        /*for (FriendlyLink friendlyLink : list) {
            System.out.println(friendlyLink);
        }*/
        System.out.println("~~请求结束~~");
    }
}
