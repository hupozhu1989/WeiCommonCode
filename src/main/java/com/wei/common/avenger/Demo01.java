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
        map.add("your-name","宋江");
        map.add("kana-name","及时雨");
        map.add("your-email","songjiang@163.com");
        map.add("age","28");
        map.add("job-timing","ok");
        map.add("tel","02-6818-1963");
        map.add("address","大阪府");
        map.add("jyob-type","パート・アルバイト");//正社員  パート・アルバイト
        map.add("job-experience","床前明余光疑是地上霜");
        map.add("your-message","举头望明月低头思故乡");
        HttpEntity requestBody = new HttpEntity(map, headers);

        ResponseEntity<Map> forEntity = null;
        Map body = null;

        for (int i = 0; i < 1; i++) {
            /*forEntity=  restTemplate.postForEntity(url, requestBody,Map.class);
            body = forEntity.getBody();
            System.err.println(body.get("message"));*/
            restTemplate.postForEntity(url, requestBody,Map.class);
        }
        System.out.println("结束");
    }


}
