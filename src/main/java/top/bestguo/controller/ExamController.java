package top.bestguo.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.bestguo.entity.Exam;
import top.bestguo.entity.Question;
import top.bestguo.entity.Student;
import top.bestguo.exception.ExamNotCompleteException;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.service.ExamService;
import top.bestguo.util.DateUtils;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    /**
     * 查询全部的考试信息
     *
     * @param classId 班级id
     * @param page 当前页
     * @param limit 当前页展示的元素个数
     * @return 返回查询结果和数据
     */
    @RequestMapping(value = "/findExam", method = RequestMethod.POST)
    @ResponseBody
    public MultipleDataResult<Exam> findExamInfoByClassId(Integer classId, Integer page, Integer limit){
        return examService.findExamInfoByClassId(classId, page, limit);
    }

    /**
     * 考试信息添加
     *
     * @param classId 班级id
     * @param examName 考试名称
     * @param examTime 考试时间
     * @param single 单选题每题分数
     * @param multiple 多选题每题分数
     * @return 添加状态
     */
    @RequestMapping(value = "/addExam", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addExam(Integer classId, String examName, String examTime,
                              Double single, Double multiple) {
        BaseResult result = new BaseResult();
        // 考试时间处理
        String[] startAndStop = examTime.split(" - ");
        // 考试开始时间和考试结束时间
        Date startTime, stopTime;
        // 将时间转成 Date
        if(startAndStop.length != 2) {
            result.setCode(1);
            result.setMessage("缺少开始时间或者结束时间");
            return result;
        } else {
            try {
                startTime = DateUtils.parseToDate("yyyy-MM-dd HH:mm:ss", startAndStop[0]);
                stopTime = DateUtils.parseToDate("yyyy-MM-dd HH:mm:ss", startAndStop[1]);
            } catch (ParseException e) {
                e.printStackTrace();
                result.setCode(1);
                result.setMessage("日期格式不正确，请重新选择");
                return result;
            }
        }
        // 考试信息实体类创建
        Exam exam = new Exam();
        exam.setExamname(examName);
        exam.setSelectone(single);
        exam.setSelectmore(multiple);
        exam.setStarttime(startTime);
        exam.setStoptime(stopTime);
        // 添加考试信息
        result = examService.addExam(exam, classId);
        return result;
    }

    /**
     * 录入题目到试卷中
     *
     * @param questionIds 题目集
     * @return 录入状态
     */
    @RequestMapping(value = "/addExamQuestion", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addExamQuestion(String questionIds, Integer isRandom) {
        // 创建考试实体类
        Exam exam = new Exam();
        exam.setQlist(questionIds);
        return examService.updateQuestionInExam(exam, isRandom == null ? 0 : isRandom);
    }

    /**
     * 随机组题，随机组题成功之后的展示
     *
     * @param single 单选题个数
     * @param multiple 多选题个数
     * @param classId 班级id
     * @return 随机生成的结果
     */
    @RequestMapping(value = "/showRandom", method = RequestMethod.POST)
    @ResponseBody
    public MultipleDataResult<Question> addExamRandom(Integer single, Integer multiple, Integer classId) {
        return examService.randomMakeExam(single, multiple, classId);
    }

    /**
     * 删除考试信息
     *
     * @param examId 考试编号
     * @return 删除状态
     */
    @RequestMapping(value = "/deleteExam", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteExam(@RequestParam("examId") Integer examId) {
        return examService.deleteExam(examId);
    }

    /**
     * 删除选中的考试信息
     *
     * @param examIds 多个考试编号
     * @return 删除状态
     */
    @RequestMapping(value = "/deleteExams", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteExams(@RequestParam("examIds") Integer[] examIds) {

        return examService.deleteExam(examIds);
    }

    /**
     * 考试试卷预览
     *
     * @param id 考试编号
     * @param model 模型
     * @return 试卷信息
     */
    @RequestMapping(value = "/paperDetail", method = RequestMethod.GET)
    public String showExamDetails(Integer id, Model model, Integer preview) {
        Map<String, Object> showExam;
        try {
            showExam = examService.showExam(id);
            model.addAttribute("examInfo", showExam);
            model.addAttribute("single", 0);
            model.addAttribute("multi", 0);
        } catch (ExamNotCompleteException e) {
            e.printStackTrace(); // 打印异常信息到控制台
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("path", "teacher/paperManage");
            // 关闭layui弹出层
            model.addAttribute("islayuilayer", true);
            return "status/fail";
        }
        return "teacher/paper_detail";
    }

    /**
     * 考试界面
     *
     * @param examId 考试编号
     * @param stuId 学生编号
     * @param model page作用域
     * @return 返回考试页面
     */
    @RequestMapping("/answerCard/{examId},{stuId}")
    public String answerCard(@PathVariable Integer examId, @PathVariable Integer stuId, Model model, HttpSession session) {
        // 得到学生的session
        Student student = (Student) session.getAttribute("student");
        // 判断考生是否在此班级中
        boolean isExistInClass;
        try {
            isExistInClass = examService.checkStudentInClass(stuId, examId);
        } catch (RuntimeException e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("path", "student/student_exam");
            return "status/fail";
        }
        if(isExistInClass) {
            Map<String, Object> showExam;
            try {
                showExam = examService.showExam(examId);
            } catch (ExamNotCompleteException e) {
                e.printStackTrace(); // 打印异常信息到控制台
                model.addAttribute("msg", e.getMessage());
                model.addAttribute("path", "student/student_exam");
                return "status/fail";
            }
            // 判断考试是否已经开始
            Date startdate = (Date) showExam.get("starttime");
            // 判断考试是否有效
            if(startdate == null) {
                model.addAttribute("msg", "此次考试老师没有设置题目和试卷总分，因此该考试无效！");
                model.addAttribute("path", "student/student_exam");
                return "status/fail";
            }
            // 判断考试是否已开始
            if(DateUtils.timeDistance(new Date(), startdate) <= 0) {
                model.addAttribute("msg", "考试未开始");
                model.addAttribute("path", "student/student_exam");
                return "status/fail";
            }
            // 判断学生是否偷看其他学生的试卷
            if(stuId.intValue() != student.getId().intValue()) {
                model.addAttribute("msg", "禁止查看其它考生的试卷！");
                model.addAttribute("path", "student/student_exam");
                return "status/fail";
            }
            model.addAttribute("examInfo", showExam);
            model.addAttribute("single", 0);
            model.addAttribute("multi", 0);
            model.addAttribute("stuId", stuId);
            examService.findAnswer(examId, stuId, model);
            // 展示答案
            examService.showAnswer(examId, stuId, model);
            String msg = (String) model.getAttribute("msg");
            if(msg != null && msg.contains("失败")){
                model.addAttribute("path", "student/student_exam");
                return "status/fail";
            }
            // 返回答题卡
            return "student/answer_card";
        } else {
            model.addAttribute("msg", "你不在这个班级，无法参加考试");
            model.addAttribute("path", "student/student_exam");
            return "status/fail";
        }
    }

    /**
     * 保存答案
     *
     * @param selectOne 单选题答案
     * @param selectMore 多选题答案
     * @param examId 考试id
     * @param stuId 学生id
     * @return 提交状态
     */
    @RequestMapping(value = "/saveAnswer", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult saveAnswer(String selectOne, String selectMore,
                                   Integer examId, Integer stuId) {

        return examService.saveAnswer(selectOne, selectMore, examId, stuId);

    }

    /**
     * 提交答案
     *
     * @param selectOne 单选题答案
     * @param selectMore 多选题答案
     * @param examId 考试id
     * @param stuId 学生id
     * @return 提交状态
     */
    @RequestMapping(value = "/commitAnswer", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult commitAnswer(String selectOne, String selectMore,
                                   Integer examId, Integer stuId) {

        return examService.commitAnswer(selectOne, selectMore, examId, stuId);

    }
    /**
     * 查询当前学生的考试成绩
     *
     * @param studentId
     * @return
     */
    @RequestMapping(value = "/studentExamPassed/{studentId}")
    @ResponseBody
    public MultipleDataResult<?> studentExamPassed(@PathVariable Integer studentId) {

        return examService.findExamPassed(studentId);
    }

    /**
     * 展示当前考试的全部成绩
     *
     * @param examId 考试id
     * @param classId 班级id
     * @param model 加载模型
     * @return
     */
    @RequestMapping(value = "/showGrades/{examId},{classId}")
    public String showGrades(@PathVariable Integer examId, @PathVariable Integer classId, Model model, Integer desc) {
        examService.printExamScore(examId, classId, model, desc);
        model.addAttribute("id", 0);
        return "teacher/scores_manage";
    }

}
