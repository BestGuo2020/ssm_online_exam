package top.bestguo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.bestguo.entity.Classes;
import top.bestguo.entity.Student;
import top.bestguo.entity.Teacher;
import top.bestguo.render.BaseResult;
import top.bestguo.render.SingleDataResult;
import top.bestguo.service.ClassesService;
import top.bestguo.service.StudentService;
import top.bestguo.service.TeacherService;

import javax.servlet.http.HttpSession;

/**
 * 学生端页面
 */
@Controller
@RequestMapping("/student")
public class  StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 学生端主页
     * @return
     */
    @RequestMapping("")
    public String main(HttpSession session, Model model) {
        Student student= (Student) session.getAttribute("student");
        // 得到id
        Integer id = student.getId();
//         查询教师信息
        model.addAttribute("student",studentService.selectStudentById(id) );
        return "student/student_index";
    }

    /**
     * 学生信息修改
     * @return
     */
    @RequestMapping("/StudentInfo")
    public String teacherInfo(HttpSession session, Model model) {
       Student student = (Student) session.getAttribute("student");
        // 得到id
        Integer id = student.getId();
        // 查询教师信息
        model.addAttribute("student", studentService.selectStudentById(id));
        return "student/student_setting";
    }

    /**
     * 学生信息的修改请求处理
     *
     * @param student 学生实体类
     * @return 响应结果
     */
    @RequestMapping(value = "/studentInfo_do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult StudentInfoDo(Student student) {
        return studentService.modifyStudentInfo(student);
    }

    /**
     * 学生密码修改
     * @return
     */
    @RequestMapping("/StudentPassword")
    public String StudentPassword() {
        return "student/student_password";
    }

    /**
     * 学生修改密码的请求处理
     *
     * @param id 教师id
     * @param old_password 前端传入的旧密码
     * @param password 前端传入的新密码
     * @return 响应结果
     */
    @RequestMapping(value = "/studentPassword_do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult StudentPasswordDo(Integer id, String old_password, String password) {
        return studentService.modifyStudentPassword(id,old_password,password);
    }

    /**
     * 取消登录请求处理
     *
     * @param session 会话
     * @return 响应结果
     */
    @RequestMapping(value = "/studentLogout")
    @ResponseBody
    public BaseResult teacherLogout(HttpSession session) {
        // 移除会话
        session.removeAttribute("student");
        // 设置返回对象
        BaseResult result = new BaseResult();
        result.setCode(0);
        result.setMessage("退出登录成功");
        return result;
    }

    /**
     * 学生端主面板
     * @return
     */
    @RequestMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        // 得到id
        Integer id = student.getId();
        // 查询学生信息
        model.addAttribute("student", student);
        return "student/student_dashboard";
    }

    /**
     * 查看我参加的班级界面
     * @return
     */
    @RequestMapping("/class_attended")
    public String classAttended() {
        return "student/class_attended";
    }
    /**
     * 查看申请加入班级界面
     * @return
     */
    @RequestMapping("/class_attend")
    public String classAttend() {
        return "student/class_attend";
    }

}
