package com.wei.common.avenger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author weizhenchao
 * @create 2020-04-29-下午 2","57
 */
public class Demo01 {
    public static void main(String[] args) {

        method01();

    }

    private static void method01() {
        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        String url = "http://www.jpcst.com/wp-json/contact-form-7/v1/contact-forms/530/feedback";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("your-name","张三");
        map.add("kana-name","大双方都");
        map.add("your-email","12345@qq.com");
        map.add("age","20");
        map.add("job-timing","发达");
        map.add("tel","爱的发的");
        map.add("address","爱的发的是");
        map.add("jyob-type","パート・アルバイト");
        map.add("job-experience","爱的方式的法第三方士大夫收到发送到");
        map.add("your-message","法师打发斯蒂芬阿道夫水电费");
        HttpEntity requestBody = new HttpEntity(map, headers);

        ResponseEntity<Map> forEntity = null;
        Map body = null;

        for (int i = 0; i < 1000; i++) {
            /*forEntity=  restTemplate.postForEntity(url, requestBody,Map.class);
            body = forEntity.getBody();
            System.err.println(body.get("message"));*/
            restTemplate.postForEntity(url, requestBody,Map.class);
        }
        System.out.println("结束");
    }


}
