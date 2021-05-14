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
//    /**
//     * 班级添加界面
//     * @return
//     */
//    @RequestMapping("/classAdd")
//    public String classAdd(Model model, @RequestParam String modify,
//                           @RequestParam(required = false) Integer classId,
//                           @RequestParam(required = false) Integer teacherId) {
//        // 判断是否为修改状态
//        if ("true".equals(modify)) {
//            // 创建班级实体类，传递参数
//            Classes classes = new Classes();
//            classes.setId(classId);
//            classes.setBelongteacher(teacherId);
//            // 查询单个
//            SingleDataResult<Classes> oneClass = classesService.findOneClass(classes);
//            model.addAttribute("oneClass", oneClass);
//        }
//        isModify(model, modify);
//        return "teacher/class_add";
//    }

    /**
     * 学生管理界面
     * @return
     */
    @RequestMapping("/studentManage")
    public String studentManage() {
        return "teacher/student_manage";
    }

    /**
     * 试卷管理界面
     * @return
     */
    @RequestMapping("/paperManage")
    public String paperManage() {
        return "teacher/paper_manage";
    }

    /**
     * 自组选题界面
     * @return
     */
    @RequestMapping("/paperAdd")
    public String paperAdd() {
        return "teacher/paper_add";
    }

    /**
     * 随机选题界面
     * @return
     */
    @RequestMapping("/paperAddRandom")
    public String paperAddRandom() {
        return "teacher/paper_add_random";
    }

    /**
     * 题库管理界面
     * @return
     */
    @RequestMapping("/tikuManage")
    public String tikuManage() {
        return "teacher/tiku_manage";
    }

    /**
     * 题库添加界面
     * @return
     */
    @RequestMapping("/tikuAdd")
    public String tikuAdd(Model model, @RequestParam String modify) {
        isModify(model, modify);
        return "teacher/tiku_add";
    }

    private void isModify(Model model, @RequestParam String modify) {
        if ("true".equals(modify)) {
            model.addAttribute("modify", "true");
            model.addAttribute("type", 2);
        } else {
            model.addAttribute("modify", "false");
            model.addAttribute("type", 1);
        }
    }

    /**
     * 成绩管理界面
     * @return
     */
    @RequestMapping("/scoresManage")
    public String scoresManage() {
        return "teacher/scores_manage";
    }

}
