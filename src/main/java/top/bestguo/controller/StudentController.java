package top.bestguo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.bestguo.entity.Classes;
import top.bestguo.entity.Student;
import top.bestguo.entity.StudentClass;
import top.bestguo.entity.Teacher;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.render.SingleDataResult;
import top.bestguo.service.ClassesService;
import top.bestguo.service.ExamService;
import top.bestguo.service.StudentService;
import top.bestguo.service.TeacherService;
import top.bestguo.vo.ClassInfo;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 学生端页面
 */
@Controller
@RequestMapping("/student")
public class  StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private ExamService examService;

    /**
     * 学生端主页
     * @return
     */
    @RequestMapping("")
    public String main(HttpSession session, Model model) {
        Student student= (Student) session.getAttribute("student");
        // 得到id
        Integer id = student.getId();
//         查询学生信息
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
        // 查询学生信息
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

        model.addAttribute("student", studentService.selectStudentById(id));

        return "student/student_dashboard";
    }

    /**
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/student_exam")
public String student_exam(HttpSession session, Model model) {
    Student student = (Student) session.getAttribute("student");
    // 得到id
    Integer id = student.getId();
    // 查询学生信息
    model.addAttribute("student", studentService.selectStudentById(id));
        MultipleDataResult<?> data = classesService.findJoinClass(id);
        model.addAttribute("data", data.getData());
        System.out.println("11111");
        System.out.println(data.getData());
        System.out.println("11111");
    return "student/student_exam";
}

    /**
     * 学生参加考试
     *
     */

    /*@RequestMapping(value = "/myexam", method = RequestMethod.POST)
    public void myExam(@RequestParam("examId") Integer examId,HttpSession session, Model model, HttpServletResponse resp) {
        Student student = (Student) session.getAttribute("student");
        // 得到id
        Integer studentId = student.getId();
        // 查询学生信息
        model.addAttribute("student", studentService.selectStudentById(studentId ));
        //得到考试id
        System.out.println("xxxxx");
        System.out.println(studentId);
        System.out.println(examId);
        System.out.println("xxxxx");
        String url="http://localhost:8080/ssm_online_exam/exam/answerCard/"+examId+","+studentId;
        String url2="https://www.baidu.com/";
        try {
            resp.sendRedirect(url2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/



    /**
 * 学生查看成绩页面
 *
 */
@RequestMapping("/studentexam_passed")
    public String studentExamPassed(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        // 得到id
        Integer id = student.getId();
        // 查询学生信息
        model.addAttribute("student", studentService.selectStudentById(id));
        return "student/studentexam_passed";
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

    /**
     * 查询班级编码
     */
    @RequestMapping("/findClassInfo")
    public String findClassInfo(Model model,Integer classCode) {
        ClassInfo classInfo = studentService.selectClassCodeByName(classCode);
        if(classInfo != null)
            model.addAttribute("classInfo",classInfo);
        return "student/class_attend";}

    /**
     * 加入班级
      */
    @RequestMapping("/joinclass")
    public String studentJoinClass(Model model, HttpSession session, Integer classId){
        StudentClass studentClass = new StudentClass();
        Student student = (Student) session.getAttribute("student");
        // 得到id
        Integer id = student.getId();
        studentClass.setStuid(id);
        Classes oneClassByClassCode = classesService.findOneClassByClassCode(classId);
        studentClass.setClassid(oneClassByClassCode.getId());
        // 判断学生已经加入
        Boolean studentAtTheClass = classesService.isStudentAtTheClass(studentClass.getClassid(), studentClass.getStuid());
        if(studentAtTheClass){
            model.addAttribute("msg", "加入班级失败，因为你已经加入了");
            model.addAttribute("path", "student/class_attend");
            return "status/fail";
        } else {
            int rows = studentService.studentJoinClass(studentClass);
            if (rows > 0) {
                model.addAttribute("msg", "加入班级成功");
                model.addAttribute("path", "student/class_attended");
                return "status/success";
            } else {
                model.addAttribute("msg", "加入班级失败");
                model.addAttribute("path", "student/class_attend");
                return "status/fail";
            }
        }
    }
}
