package com.wei.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.wei.common.resttemplate.ApiResult;
import com.wei.common.resttemplate.FriendlyLink;
import com.wei.springboot.entity.TStudent;
import com.wei.springboot.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private StudentService studentService;

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

    //http://localhost:8088/mycontroller/queryStudent/1
    @ApiOperation("根据id获取学生")
    @GetMapping("/queryStudent/{id}")
    public ApiResult<TStudent> queryStudentById(@PathVariable("id") Integer id){
        TStudent tStudent = studentService.queryStudentById(id);
        return new ApiResult<>("000","OK",tStudent);
    }

    //http://localhost:8088/mycontroller/queryStudentPage
    @ApiOperation("分页获取学生")
    @PostMapping("/queryStudentPage")
    public ApiResult<PageInfo> queryStudentPage(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="5")int pageSize){
        PageInfo<TStudent> pageInfo = studentService.queryStudentPage(pageNo, pageSize);
        return new ApiResult<>("000","OK",pageInfo);
    }

}
