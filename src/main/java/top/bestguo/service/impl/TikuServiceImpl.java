package top.bestguo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bestguo.entity.Question;
import top.bestguo.mapper.QuestionMapper;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.service.TikuService;
import top.bestguo.vo.QuestionCondition;

import java.util.Arrays;
import java.util.List;

/**
 * 题库服务实现类
 */
@Service("tikuService")
public class TikuServiceImpl implements TikuService {

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 添加题目到班级的题库中
     *
     * @param question 问题实体类
     * @return 添加成功与否
     */
    @Override
    public BaseResult addQuestion(Question question) {
        BaseResult result = new BaseResult();
        int insert = questionMapper.insert(question);
        if(insert > 0) {
            result.setCode(0);
            result.setMessage("题目添加成功");
        } else {
            result.setCode(1);
            result.setMessage("题目添加失败");
        }
        return result;
    }

    /**
     * 修改题目到班级的题库中
     *
     * @param question 问题实体类
     * @return 修改成功与否
     */
    @Override
    public BaseResult modifyQuestion(Question question) {
        BaseResult result = new BaseResult();
        int insert = questionMapper.updateById(question);
        if(insert > 0) {
            result.setCode(0);
            result.setMessage("题目修改成功");
        } else {
            result.setCode(1);
            result.setMessage("题目修改失败，该题目不存在");
        }
        return result;
    }

    /**
     * 删除题目到班级的题库中
     *
     * @param questionId 问题实体类
     * @return 删除成功与否
     */
    @Override
    public BaseResult deleteQuestion(Integer questionId) {
        BaseResult result = new BaseResult();
        int insert = questionMapper.deleteById(questionId);
        if(insert > 0) {
            result.setCode(0);
            result.setMessage("题目删除成功");
        } else {
            result.setCode(1);
            result.setMessage("题目删除失败，该题目不存在");
        }
        return result;
    }

    /**
     * 查询一个题目
     *
     * @param questionId 问题id
     * @return 删除成功与否
     */
    @Override
    public Question findQuestionById(Integer questionId) {
        return questionMapper.selectById(questionId);
    }

    /**
     * 查询当前班级中的全部题目
     *
     * @param classId 班级id
     * @return 多个问题记录和状态
     */
    @Override
    public MultipleDataResult<Question> findAllQuestion(Integer classId) {
        MultipleDataResult<Question> result = new MultipleDataResult<>();
        // 设置查询条件
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("belongclass", classId);
        // 查询问题
        List<Question> questions = questionMapper.selectList(questionQueryWrapper);

        return getQuestionMultipleDataResult(questions, null);
    }

    /**
     * 查询当前班级中的题目，带分页
     *
     * @param question 问题实体类
     * @param p 当前页数
     * @param limit 当前页展示的数据条数
     * @return 当前页的数据
     */
    @Override
    public MultipleDataResult<Question> findAllQuestion(QuestionCondition question, Integer p, Integer limit) {
        MultipleDataResult<Question> result = new MultipleDataResult<>();
        // 设置查询条件
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("belongclass", question.getBelongclass());
        // 按问题名模糊查询
        String questionname = question.getQuestionname();
        if(questionname != null && !"".equals(questionname)){
            questionQueryWrapper.like("questionname", questionname);
        }
        // 按问题难度系数查询
        String levels = question.getLevels();
        if(levels != null && !"".equals(levels)){
            // 切分字符串
            String[] levelArr = levels.split(",");
            // 保存
            Integer[] integers = new Integer[levelArr.length];
            for (int i = 0; i < integers.length; i++) {
                integers[i] = new Integer(levelArr[i]);
            }
            questionQueryWrapper.in("level", Arrays.asList(integers));
        }
        // 按选择类型查询
        Boolean ismulti = question.getIsmulti();
        if(ismulti != null){
            questionQueryWrapper.eq("ismulti", ismulti);
        }
        // 设置分页器
        Page<Question> page = new Page<>(p, limit);
        // 查询问题
        List<Question> questions = questionMapper.selectPage(page, questionQueryWrapper).getRecords();

        return getQuestionMultipleDataResult(questions, page);
    }

    /**
     * 单独封装的返回结果
     *
     * @param list 查询到的数据
     * @param page 分页器
     * @return 当前页的班级信息或者所有的班级信息
     */
    private MultipleDataResult<Question> getQuestionMultipleDataResult(List<Question> list, Page<Question> page) {
        // 返回数据
        MultipleDataResult<Question> dataResult = new MultipleDataResult<>();
        // 设置数据
        dataResult.setData(list);
        dataResult.setCode(0);
        // 判断是否传入分页器参数
        if(page != null) {
            dataResult.setTotal((int) page.getTotal());
        }
        else {
            dataResult.setTotal(list.size());
        }
        return dataResult;
    }
}
