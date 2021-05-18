package top.bestguo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.bestguo.entity.Exam;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.service.ExamService;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @RequestMapping(value = "/findExam", method = RequestMethod.POST)
    @ResponseBody
    public MultipleDataResult<Exam> findExamInfoByClassId(Integer classId, Integer page, Integer limit){
        return examService.findExamInfoByClassId(classId, page, limit);
    }

}
