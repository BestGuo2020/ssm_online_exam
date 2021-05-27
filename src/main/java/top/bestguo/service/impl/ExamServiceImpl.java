package top.bestguo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.bestguo.entity.*;
import top.bestguo.mapper.ClassesMapper;
import top.bestguo.mapper.ExamClassMapper;
import top.bestguo.mapper.ExamMapper;
import top.bestguo.mapper.QuestionMapper;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.service.ExamService;
import top.bestguo.util.RandomUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 考试服务实现类
 */
@Service("examService")
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ExamClassMapper examClassMapper;
    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 通过班级id查询当前班级的考试信息
     *
     * @param classId 班级号
     * @param page 当前页
     * @param limit 当前页展示的数据条数
     * @return 学生信息
     */
    @Override
    public MultipleDataResult<Exam> findExamInfoByClassId(Integer classId, Integer page, Integer limit) {
        MultipleDataResult<Exam> result = new MultipleDataResult<>();
        // 分页
        PageHelper.startPage(page, limit);
        // 查询
        List<Exam> exams = examMapper.findExamInfoByClassId(classId);
        // 获取返回的信息
        PageInfo<Exam> pageInfo = new PageInfo<>(exams);
        // 设置总计数
        result.setTotal((int) pageInfo.getTotal());
        // 设置返回结果
        result.setData(pageInfo.getList());
        // 设置状态码和消息
        if(pageInfo.getList().size() > 0) {
            result.setCode(0);
            result.setMessage("查询成功");
        } else {
            result.setCode(1);
            result.setMessage("无结果");
        }

        return result;
    }

    /**
     * 考试添加的实现方法
     *
     * @param exam 考试实体类
     * @param classId 班级id
     * @return 添加状态
     */
    @Override
    public BaseResult addExam(Exam exam, Integer classId) {
        BaseResult result = new BaseResult();
        // 创建考试和班级的关联实体
        ExamClass examClass = new ExamClass();
        // 设置班级id
        examClass.setClassid(classId);
        // 插入考试信息
        int examRes = examMapper.insert(exam);
        // 判断是否插入成功
        if(examRes > 0) {
            // 查询最近的考试信息id
            Integer examId = examMapper.findExamRecent();
            // 查询当前班级信息
            Classes classes = classesMapper.selectById(classId);
            // 判断班级是否存在
            if(classes != null) {
                // 设置考试id
                examClass.setExamid(examId);
                int insert = examClassMapper.insert(examClass);
                if(insert > 0) {
                    result.setCode(0);
                    result.setMessage("考试信息添加成功，接下来可以开始选题了");
                }
            } else {
                // 删除当前考试信息
                examMapper.deleteById(examId);
                result.setCode(1);
                result.setMessage("添加失败，该班级不存在");
            }
        } else {
            result.setCode(1);
            result.setMessage("考试信息添加失败");
        }
        return result;
    }

    /**
     * 录入题目的考试方法
     *
     * @param exam 考试实体类
     * @return 试题录入状态
     */
    @Override
    public BaseResult updateQuestionInExam(Exam exam) {
        BaseResult result = new BaseResult();
        // 查询最近的考试信息id
        Integer recent = examMapper.findExamRecent();
        exam.setId(recent);
        // 更新考题
        int res = examMapper.updateById(exam);
        if(res > 0) {
            result.setCode(0);
            result.setMessage("题目录入成功！");
        } else {
            result.setCode(1);
            result.setMessage("题目录入失败！");
        }
        return result;
    }

    /**
     * 随机组题方法实现
     *
     * @param single 单选题总数
     * @param multiple 多选题总数
     * @param classId 班级id
     * @return
     */
    @Override
    public MultipleDataResult<Question> randomMakeExam(Integer single, Integer multiple, Integer classId) {
        MultipleDataResult<Question> result = new MultipleDataResult<>();
        // 新的问题数组
        List<Question> questionList = new ArrayList<>();
        // 查询出全部的单选题
        List<Question> questionSingle = questionMapper
                .selectList(new QueryWrapper<Question>().eq("belongclass", classId).eq("ismulti", 0));
        // 查询出全部的多选题
        List<Question> questionMulti = questionMapper
                .selectList(new QueryWrapper<Question>().eq("belongclass", classId).eq("ismulti", 1));
        // 得到单选题和多选题的全部题目数
        int singleCount = questionSingle.size(), multiCount = questionMulti.size();
        // 判断单选题和多选题数量之和是否超过10个，未超过10个无法进行随机组卷
        if(singleCount + multiCount < 10) {
            result.setCode(1);
            result.setMessage("随机组题失败，题目数未超过10个");
        } else {
            // 单选题组合
            try {
                LinkedHashSet<Integer> randomSingle = RandomUtils.getRandomInt(single, singleCount);
                for (Integer i : randomSingle) {
                    questionList.add(questionSingle.get(i));
                }
            } catch (IllegalArgumentException ex) {
                result.setCode(1);
                result.setMessage("随机组题失败，指定生成的单选题数比题库中的单选题数还要多");
                result.setData(null);
                result.setTotal(questionList.size());
                return result;
            }

            // 多选题组合
            try {
                LinkedHashSet<Integer> randomMultiple = RandomUtils.getRandomInt(multiple, multiCount);
                for (Integer i : randomMultiple) {
                    questionList.add(questionMulti.get(i));
                }
            } catch (IllegalArgumentException ex) {
                result.setCode(1);
                result.setMessage("随机组题失败，指定生成的多选题数比题库中的多选题数还要多");
                result.setData(null);
                result.setTotal(questionList.size());
                return result;
            }

            result.setCode(0);
            result.setMessage("随机组题成功，等待您的确认！");
            result.setData(questionList);
            result.setTotal(questionList.size());

        }
        return result;
    }
}
