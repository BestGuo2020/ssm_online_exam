package top.bestguo.controller;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.bestguo.entity.Student;
import top.bestguo.entity.Teacher;
import top.bestguo.service.RegisterService;

@Controller
public class RegisterController {
@Autowired
private RegisterService registerService;

    @RequestMapping("/register")
    public  String register(){
        return "register";
    }
    @RequestMapping(value = "/registerdo",method = RequestMethod.POST)
    @ResponseBody
    public String registerdo(String email,Integer number,String username,String password,Integer gender,String type) {

        if(type.equals("1")){
            Student student=new Student();
            student.setEmail(email);
            student.setStuid(number);
            student.setUsername(username);
            student.setPassword(password);
            student.setGender(gender);
            try {
                int rows = registerService.addStudent(student);
                if(rows>0){
                    return "ok" ;
                }
            } catch (Exception e) {
                return "fail";
            }

        }

        if(type.equals("2")){
            Teacher teacher = new Teacher();
            teacher.setEmail(email);
            teacher.setTeacherid(number);
            teacher.setUsername(username);
            teacher.setPassword(password);
            teacher.setGender(gender);
            try {
                int rows = registerService.addTeacher(teacher);
                if(rows>0){
                    return "ok" ;
                }
            } catch (Exception e) {
                return "fail";
            }
            return  "fail";
        }

        return "fail";
    }
}
