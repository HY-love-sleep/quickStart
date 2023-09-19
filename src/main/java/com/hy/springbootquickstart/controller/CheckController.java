package com.hy.springbootquickstart.controller;

import com.hy.springbootquickstart.Enum.CheckEnum;
import com.hy.springbootquickstart.service.impl.CheckCompareFactory;
import com.hy.springbootquickstart.service.impl.CheckPetServiceImpl;
import com.hy.springbootquickstart.service.impl.CheckUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Description: 测试controller层
 * Author: yhong
 * Date: 2023/9/17
 */
@RestController
@RequestMapping("/hello")
public class CheckController {
    @Autowired
    private CheckUserServiceImpl checkUserService;

    @Autowired
    private CheckPetServiceImpl checkPetService;

    @Autowired
    private CheckCompareFactory checkCompareFactory;

    @RequestMapping("/check")
    public String check() throws IOException {
        checkUserService.checkDetails("C:\\My_Work\\IdeaProjects\\Scaffolding\\demo\\src\\main\\java\\code_optimization\\A.txt", "C:\\My_Work\\IdeaProjects\\Scaffolding\\demo\\src\\main\\java\\code_optimization\\B.txt");
        checkPetService.checkDetails("C:\\My_Work\\IdeaProjects\\Scaffolding\\demo\\src\\main\\java\\code_optimization\\C.txt", "C:\\My_Work\\IdeaProjects\\Scaffolding\\demo\\src\\main\\java\\code_optimization\\D.txt");
        checkCompareFactory.checkCompare(CheckEnum.USER, "C:\\My_Work\\IdeaProjects\\Scaffolding\\demo\\src\\main\\java\\code_optimization\\A.txt", "C:\\My_Work\\IdeaProjects\\Scaffolding\\demo\\src\\main\\java\\code_optimization\\B.txt");
        return "hello template";
    }
}
