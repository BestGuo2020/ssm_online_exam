package top.bestguo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 教师端页面
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    /**
     * 教师端主页
     * @return
     */
    @RequestMapping("")
    public String main() {
        return "teacher/teacher_index";
    }

    /**
     * 教师信息修改
     * @return
     */
    @RequestMapping("/teacherInfo")
    public String teacherInfo() {
        return "teacher/teacher_setting";
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
     * 教师主面板
     * @return
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
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
        if("true".equals(modify)) {
            model.addAttribute("modify", "true");
        } else {
            model.addAttribute("modify", "false");
        }
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
     * 考试管理界面
     * @return
     */
    @RequestMapping("/examManage")
    public String examManage() {
        return "teacher/exam_manage";
    }

    /**
     * 考试添加界面
     * @return
     */
    @RequestMapping("/examAdd")
    public String examAdd() {
        return "teacher/exam_add";
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
        if("true".equals(modify)) {
            model.addAttribute("modify", "true");
        } else {
            model.addAttribute("modify", "false");
        }
        return "teacher/tiku_add";
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
