package top.bestguo.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.bestguo.entity.Exam;
import top.bestguo.entity.Question;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.service.ExamService;
import top.bestguo.util.DateUtils;

import java.text.ParseException;
import java.util.Date;

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
                              Integer single, Integer multiple) {
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
    public BaseResult addExamQuestion(String questionIds) {
        // 创建考试实体类
        Exam exam = new Exam();
        exam.setQlist(questionIds);
        return examService.updateQuestionInExam(exam);
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

}
