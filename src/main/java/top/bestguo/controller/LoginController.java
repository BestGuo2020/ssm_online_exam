package top.bestguo.controller;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.bestguo.entity.Student;
import top.bestguo.entity.Teacher;
import top.bestguo.service.LoginService;

@Controller
public class LoginController {
@Autowired
private LoginService loginService;
    @RequestMapping("/login")
    public  String login(){
        return "login";
    }
    @RequestMapping(value = "/logindo",method = RequestMethod.POST)
    @ResponseBody
    public String logindo(String email,String password,String type) {

        if(type.equals("1")){
            Student student=loginService.findStudent(email,password);
            if(student!=null){
                return "student" ;

            }
            return  "fail";

        }
        if(type.equals("2")){
            Teacher teacher=loginService.findTeacher(email,password);
            if(teacher!= null){
                return "teacher";
            }
            return  "fail";
        }



        return "fail";
    }

}
