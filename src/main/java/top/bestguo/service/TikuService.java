package top.bestguo.service;

import org.springframework.web.multipart.MultipartFile;
import top.bestguo.entity.Question;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.vo.QuestionCondition;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 题库服务类
 */
public interface TikuService {

    /**
     * 添加题目到班级的题库中
     *
     * @param question 问题实体类
     * @return 添加成功与否
     */
    BaseResult addQuestion(Question question);

    /**
     * 修改题目到班级的题库中
     *
     * @param question 问题实体类
     * @return 修改成功与否
     */
    BaseResult modifyQuestion(Question question);

    /**
     * 删除题目到班级的题库中
     *
     * @param questionId 问题id
     * @return 删除成功与否
     */
    BaseResult deleteQuestion(Integer questionId);

    /**
     * 查询一个题目
     *
     * @param questionId 问题id
     * @return 问题实体类
     */
    Question findQuestionById(Integer questionId);

    /**
     * 查询当前班级中的全部题目
     *
     * @param classId 班级id
     * @return 多个问题记录和状态
     */
    MultipleDataResult<Question> findAllQuestion(Integer classId);

    /**
     * 查询当前班级中的全部题目，带分页
     *
     * @param question 问题实体类
     * @param p 当前页数
     * @param limit 当前页展示的数据条数
     * @return 当前页的数据
     */
    MultipleDataResult<Question> findAllQuestion(QuestionCondition question, Integer p, Integer limit);

    /**
     * 批量导入题目到题库中
     *
     * @param file
     * @return
     */
    BaseResult importQuestion(InputStream file, String fileExt, Integer belongClass) throws IOException;

    /**
     * 删除多个问题
     *
     * @param questionId
     * @return
     */
    BaseResult deleteQuestionMore(Integer[] questionId);

}
