package top.bestguo.controller;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.bestguo.entity.Student;
import top.bestguo.entity.Teacher;
import top.bestguo.service.LoginService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logindo", method = RequestMethod.POST)
    @ResponseBody
    public String logindo(HttpSession session, @RequestParam("email") String email,
                          @RequestParam("password") String password,
                          @RequestParam("type") String type) {

        if (type.equals("1")) {
            Student student = loginService.findStudent(email, password);
            if (student != null) {
                // 添加 session
                session.setAttribute("student", student);
                return "student";

            }
            return "fail";

        }
        if (type.equals("2")) {
            Teacher teacher = loginService.findTeacher(email, password);
            if (teacher != null) {
                session.setAttribute("teacher", teacher);
                return "teacher";
            }
            return "fail";
        }


        return "fail";
    }

}
