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
 * @create 2020-05-27-下午 5:52
 */
public class Demo02 {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        String url = "https://jiekuan.p2peye.com/user/SendCode";
        String cellphone = "18565686634";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("no_validation","1");
        map.add("mobile",cellphone);
        HttpEntity requestBody = new HttpEntity(map, headers);

        ResponseEntity<Map> forEntity = null;
        /*Map body = null;
        forEntity=  restTemplate.postForEntity(url, requestBody,Map.class);
        body = forEntity.getBody();*/
        restTemplate.postForEntity(url, requestBody,Map.class);
        System.out.println("结束");




    }




}
