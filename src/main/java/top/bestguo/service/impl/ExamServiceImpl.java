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
import top.bestguo.util.DateUtils;
import top.bestguo.util.RandomUtils;

import java.util.*;

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
        // 查询考试
        exam.setId(recent);
        // 总分计算
        String qlist = exam.getQlist();
        if(qlist != null) {
            String[] questionIds = qlist.split(",");
            // 查询选中的题目，判断是不是单选还是多选
            int single = 0, multi = 0;
            for (String questionId : questionIds) {
                Question question = questionMapper.selectById(Integer.parseInt(questionId));
                // 判断是多选还是单选，并统计其个数
                if(question.getIsmulti()) {
                    multi++;
                } else {
                    single++;
                }
                // 计算总和
                Exam exam2 = examMapper.selectById(recent);
                int score = exam2.getSelectone() * single + exam2.getSelectmore() * multi;
                // 保存总和
                exam.setScore(score);
            }
        }
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
     * @return 返回随机抽取的题目
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
                result.setMessage("随机组题失败，指定生成的多选题数多于题库中的多选题数");
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

    /**
     * 删除
     *
     * @param examId 考试编号
     * @return 删除状态
     */
    @Override
    public BaseResult deleteExam(Integer examId) {
        BaseResult result = new BaseResult();
        // 删除考试表的中考试信息
        int res = examMapper.deleteById(examId);
        if(res > 0) {
            result.setCode(0);
            result.setMessage("删除成功！");
        } else {
            result.setCode(1);
            result.setMessage("删除失败，该考试已经删除！");
        }
        return result;
    }

    /**
     * 删除多条考试编号
     *
     * @param examId 考试编号
     * @return 删除状态
     */
    @Override
    public BaseResult deleteExam(Integer[] examId) {
        BaseResult result = new BaseResult();
        // 删除考试表的中考试信息
        int res = examMapper.deleteBatchIds(Arrays.asList(examId));
        if(res > 0) {
            result.setCode(0);
            result.setMessage("选中的考试删除成功！");
        } else {
            result.setCode(1);
            result.setMessage("删除失败，选中的考试已经删除！");
        }
        return result;
    }

    /**
     * 展示试卷的实现类
     *
     * @param id 试卷id
     * @return 当前考试对应的题目集
     */
    @Override
    public Map<String, Object> showExam(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Exam exam = examMapper.selectById(id);
        // 查询题目集编号
        String qlist = exam.getQlist();
        // 题目数组
        if(qlist != null) {
            ArrayList<Question> questions = new ArrayList<>();
            String[] questionIds = qlist.split(",");
            // 添加试卷到问题集中
            for (String questionId : questionIds) {
                // 查询问题
                Question question = questionMapper.selectById(Integer.parseInt(questionId));
                questions.add(question);
            }
            // 添加试卷信息
            map.put("questions", questions); // 题目集
            map.put("single", exam.getSelectone()); // 单选分数
            map.put("multi", exam.getSelectmore()); // 多选分数
            map.put("score", exam.getScore()); // 总分
            map.put("name", exam.getExamname()); // 考试名称
            map.put("time", DateUtils.timeDistance(exam.getStoptime(), exam.getStarttime())); // 考试时间
        }
        return map;
    }
}
