package top.bestguo.service;

import top.bestguo.entity.Exam;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;

/**
 * 考试服务接口类
 */
public interface ExamService {

    /**
     * 查询考试信息，通过班级id
     *
     * @param classId 班级id
     * @param page 当前页
     * @param limit 当前页所展示的页面数
     * @return 返回结果
     */
    MultipleDataResult<Exam> findExamInfoByClassId(Integer classId, Integer page, Integer limit);

    /**
     * 添加考试信息
     *
     * @param exam 考试实体类
     * @param classId 班级id
     * @return 添加状态
     */
    BaseResult addExam(Exam exam, Integer classId);

}
