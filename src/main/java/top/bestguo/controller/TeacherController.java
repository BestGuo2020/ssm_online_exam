package top.bestguo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.bestguo.entity.Classes;
import top.bestguo.entity.Question;
import top.bestguo.entity.Teacher;
import top.bestguo.render.BaseResult;
import top.bestguo.render.SingleDataResult;
import top.bestguo.service.ClassesService;
import top.bestguo.service.TeacherService;
import top.bestguo.service.TikuService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 教师端页面
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private TikuService tikuService;

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
     * 取消登录请求处理
     *
     * @param session 会话
     * @return 响应结果
     */
    @RequestMapping(value = "/teacherLogout")
    @ResponseBody
    public BaseResult teacherLogout(HttpSession session) {
        // 移除会话
        session.removeAttribute("teacher");
        // 设置返回对象
        BaseResult result = new BaseResult();
        result.setCode(0);
        result.setMessage("退出登录成功");
        return result;
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
    public String classAdd(Model model, @RequestParam String modify,
                           @RequestParam(required = false) Integer classId,
                           @RequestParam(required = false) Integer teacherId) {
        // 判断是否为修改状态
        if ("true".equals(modify)) {
            // 创建班级实体类，传递参数
            Classes classes = new Classes();
            classes.setId(classId);
            classes.setBelongteacher(teacherId);
            // 查询单个
            SingleDataResult<Classes> oneClass = classesService.findOneClass(classes);
            model.addAttribute("oneClass", oneClass);
        }
        isModify(model, modify);
        return "teacher/class_add";
    }

    /**
     * 学生管理界面
     * @return
     */
    @RequestMapping("/studentManage")
    public String studentManage(HttpSession session, Model model) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Classes> data = classesService.findAllClass(teacher.getId()).getData();
        model.addAttribute("data", data);
        return "teacher/student_manage";
    }

    /**
     * 试卷管理界面
     * @return
     */
    @RequestMapping("/paperManage")
    public String paperManage(HttpSession session, Model model) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Classes> data = classesService.findAllClass(teacher.getId()).getData();
        model.addAttribute("data", data);

        return "teacher/paper_manage";
    }

    /**
     * 自组选题界面
     * @return
     */
    @RequestMapping("/paperAdd")
    public String paperAdd(HttpSession session, Model model) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Classes> data = classesService.findAllClass(teacher.getId()).getData();
        model.addAttribute("data", data);
        return "teacher/paper_add";
    }

    /**
     * 随机选题界面
     * @return
     */
    @RequestMapping("/paperAddRandom")
    public String paperAddRandom(HttpSession session, Model model) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Classes> data = classesService.findAllClass(teacher.getId()).getData();
        model.addAttribute("data", data);
        return "teacher/paper_add_random";
    }

    /**
     * 题库管理界面
     * @return
     */
    @RequestMapping("/tikuManage")
    public String tikuManage(HttpSession session, Model model) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Classes> data = classesService.findAllClass(teacher.getId()).getData();
        model.addAttribute("data", data);
        return "teacher/tiku_manage";
    }

    /**
     * 题库添加界面
     *
     * @param session
     * @param model
     * @param modify 是否为修改状态
     * @param id 题目id
     * @return
     */
    @RequestMapping("/tikuAdd")
    public String tikuAdd(HttpSession session, Model model,
                          @RequestParam String modify, @RequestParam(required = false) Integer id) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        // 加载班级界面
        List<Classes> data = classesService.findAllClass(teacher.getId()).getData();
        model.addAttribute("data", data);
        // 判断修改状态
        if ("true".equals(modify)) {
            Question question = tikuService.findQuestionById(id);
            model.addAttribute("question", question);
        }
        // 展示内容给
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

}
