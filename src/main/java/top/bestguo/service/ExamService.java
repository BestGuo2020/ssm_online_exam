package top.bestguo.service;

import org.springframework.ui.Model;
import top.bestguo.entity.Exam;
import top.bestguo.entity.Question;
import top.bestguo.exception.ExamNotCompleteException;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;

import java.util.List;
import java.util.Map;

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

    /**
     * 更新考试题目，同时也是自主建卷的方法
     *
     * @param exam 考试实体类
     * @return 更新状态
     */
    BaseResult updateQuestionInExam(Exam exam, Integer isRandom);

    /**
     * 更新试卷题目，随机组题
     *
     * @param single 单选题总数
     * @param multiple 多选题总数
     * @param classId 班级id
     * @return 更新状态
     */
    MultipleDataResult<Question> randomMakeExam(Integer single, Integer multiple, Integer classId);

    /**
     * 删除考试
     *
     * @param examId 考试编号
     * @return 删除状态
     */
    BaseResult deleteExam(Integer examId);

    /**
     * 删除多个考试
     *
     * @param examId 考试编号
     * @return 删除状态
     */
    BaseResult deleteExam(Integer[] examId);

    /**
     * 展示试卷
     *
     * @param id 试卷id
     * @return 当前考试对应的题目集
     */
    Map<String, Object> showExam(Integer id) throws ExamNotCompleteException;

    /**
     * 判断该学生是否在这个班级中
     *
     * @param stuId 学生id
     * @param examId 考试id
     * @return true-在班上，false-不在班上
     */
    boolean checkStudentInClass(Integer stuId, Integer examId);

    /**
     * 保存答案
     *
     * @param selectOne 单选题答案
     * @param selectMore 多选题答案
     * @param examId 考试id
     * @param stuId 学生id
     */
    BaseResult saveAnswer(String selectOne, String selectMore, Integer examId, Integer stuId);

    /**
     * 提交试卷并批改
     *
     * @param selectOne 单选题答案
     * @param selectMore 多选题答案
     * @param examId 考试id
     * @param stuId 学生id
     */
    BaseResult commitAnswer(String selectOne, String selectMore, Integer examId, Integer stuId);

    /**
     * 加载保存在服务器的答案
     *
     * @param examId 考试id
     * @param stuId 学生id
     * @param model 视图层模型
     * @return 保存的答案
     */
    void findAnswer(Integer examId, Integer stuId, Model model);

    /**
     * 考试结束后将答案和解析展示出来
     *
     * @param examId 考试id
     * @param stuId 学生id
     * @param model 视图层模型
     */
    void showAnswer(Integer examId, Integer stuId, Model model);

    /**
     * 查询学生的考试成绩
     *
     * @param studentId 学生id
     * @return 返回状态和成绩数据
     */
    MultipleDataResult<?> findExamPassed(Integer studentId);

    /**
     *通过学生id查找考试id
     * @param studentId
     * @return
     */
    int findExamId(Integer studentId);

    /**
     * 输出考试成绩
     *
     * @param examId 考试id
     * @param model 视图层模型
     * @param isDesc 是否逆序  1-是，0-不是
     */
    void printExamScore(Integer classId, Integer examId, Model model, Integer isDesc);
}
