package top.bestguo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.bestguo.entity.Teacher;
import top.bestguo.render.BaseResult;
import top.bestguo.service.TeacherService;

import javax.servlet.http.HttpSession;

/**
 * 教师端页面
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 教师端主页
     * @return
     */
    @RequestMapping("")
    public String main(HttpSession session, Model model) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        // 得到id
        Integer id = teacher.getId();
        // 查询教师信息
        model.addAttribute("teacher", teacherService.selectTeacherById(id));
        return "teacher/teacher_index";
    }

    /**
     * 教师信息修改
     * @return
     */
    @RequestMapping("/teacherInfo")
    public String teacherInfo(HttpSession session, Model model) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        // 得到id
        Integer id = teacher.getId();
        // 查询教师信息
        model.addAttribute("teacher", teacherService.selectTeacherById(id));
        return "teacher/teacher_setting";
    }

    /**
     * 教师信息的修改请求处理
     *
     * @param teacher 教师实体类
     * @return 响应结果
     */
    @RequestMapping(value = "/teacherInfo_do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult teacherInfoDo(Teacher teacher) {
        return teacherService.modifyTeacherInfo(teacher);
    }

    /**
     * 教师密码修改
     * @return
     */
    @RequestMapping("/teacherPassword")
    public String teacherPassword() {
        return "teacher/teacher_password";
    }

    /**
     * 教师修改密码的请求处理
     *
     * @param id 教师id
     * @param old_password 前端传入的旧密码
     * @param password 前端传入的新密码
     * @return 响应结果
     */
    @RequestMapping(value = "/teacherPassword_do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult teacherPasswordDo(Integer id, String old_password, String password) {
        return teacherService.modifyTeacherPassword(id, old_password, password);
    }

    /**
     * 教师主面板
     * @return
     */
    @RequestMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        // 得到id
        Integer id = teacher.getId();
        // 查询教师信息
        model.addAttribute("teacher", teacherService.selectTeacherById(id));
        return "teacher/teacher_dashboard";
    }

    /**
     * 班级管理界面
     * @return
     */
    @RequestMapping("/classManage")
    public String classManager() {
        return "teacher/class_manager";
    }

    /**
     * 班级添加界面
     * @return
     */
    @RequestMapping("/classAdd")
    public String classAdd(Model model, @RequestParam String modify) {
        isModify(model, modify);
        return "teacher/class_add";
    }

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
        } else {
            model.addAttribute("modify", "false");
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
