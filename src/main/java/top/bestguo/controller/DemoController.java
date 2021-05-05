package top.bestguo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import top.bestguo.service.impl.DemoServiceImpl;

@Controller
@RequestMapping("/test")
public class DemoController {

    @Autowired
    @Qualifier("demoService")
    private DemoServiceImpl demoService;

    @RequestMapping("/demo")
    public String demo(){
        demoService.findAllService();
        return "test/demo";
    }

    @RequestMapping("/answer")
    public String answer() {
        return "test/answer_card";
    }

}
