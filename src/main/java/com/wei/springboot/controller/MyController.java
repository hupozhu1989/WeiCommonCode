package com.wei.springboot.controller;

import com.wei.common.resttemplate.ApiResult;
import com.wei.common.resttemplate.FriendlyLink;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/6 0006
 */
@Api(value = "我的")
@RestController()
@RequestMapping("/mycontroller")
public class MyController {

    @ApiOperation("获取友情链接")
    @GetMapping("/queryFriendlyLinks")
    public ApiResult<List<FriendlyLink>> queryFriendlyLinks(){
        List<FriendlyLink> list = new ArrayList<>();
        list.add(new FriendlyLink(45,"1433459417000","中国票据网","http://www.zgpj.net/"));
        list.add(new FriendlyLink(55,"1444373152000","南方财富网","http://www.southmoney.com/"));
        list.add(new FriendlyLink(65,"1462862908000","网贷东方","http://www.wangdaidongfang.com"));
        list.add(new FriendlyLink(75,"1466405348000","信用评级","http://www.pj.com/news/pingji/"));
        list.add(new FriendlyLink(85,"1484895149000","网贷之家","http://www.wdzj.com"));
        list.add(new FriendlyLink(85,"1526437500000","红岭创投","https://www.my089.com/"));
        return new ApiResult<>("000","OK",list);
    }

}
