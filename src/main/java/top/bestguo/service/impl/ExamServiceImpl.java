package top.bestguo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import top.bestguo.entity.*;
import top.bestguo.exception.ExamNotCompleteException;
import top.bestguo.mapper.*;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.service.ExamService;
import top.bestguo.util.DateUtils;
import top.bestguo.util.RandomUtils;
import top.bestguo.vo.GradeTable;

import java.util.*;

/**
 * 考试服务实现类
 */
@Service("examService")
@Transactional(rollbackFor = Exception.class)
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ExamClassMapper examClassMapper;
    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private StudentClassMapper studentClassMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 通过班级id查询当前班级的考试信息
     *
     * @param classId 班级号
     * @param page    当前页
     * @param limit   当前页展示的数据条数
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
        if (pageInfo.getList().size() > 0) {
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
     * @param exam    考试实体类
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
        if (examRes > 0) {
            // 查询最近的考试信息id
            Integer examId = examMapper.findExamRecent();
            // 查询当前班级信息
            Classes classes = classesMapper.selectById(classId);
            // 判断班级是否存在
            if (classes != null) {
                // 设置考试id
                examClass.setExamid(examId);
                int insert = examClassMapper.insert(examClass);
                if (insert > 0) {
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
     * @param isRandom 判断是否为随机的？1-是，0-不是
     * @return 试题录入状态
     */
    @Override
    public BaseResult updateQuestionInExam(Exam exam, Integer isRandom) {
        // 题目编号拆分
        String qlist = exam.getQlist();
        // 如果不是随机组卷，则单选和多选分开
        if(isRandom != 1) {
            // 自主选题的题目集
            String[] questionIds = qlist.split(",");
            // 单选题列表和多选题列表
            List<String> questionSingle = new ArrayList<>(), questionMulti = new ArrayList<>();
            for (String questionId : questionIds) {
                Question question = questionMapper.selectById(Integer.parseInt(questionId));
                // 判断是多选还是单选
                if (question.getIsmulti()) {
                    questionMulti.add(questionId);
                } else {
                    questionSingle.add(questionId);
                }
            }
            // 多选题的数组合并到单选中
            questionSingle.addAll(questionMulti);
            // 分开的题目将保存到考试中
            String qlist2 = StringUtils.join(questionSingle, ",");
            exam.setQlist(qlist2);
        }
        BaseResult result = new BaseResult();
        // 查询最近的考试信息id
        Integer recent = examMapper.findExamRecent();
        // 查询考试
        exam.setId(recent);
        // 总分计算
        if (qlist != null) {
            String[] questionIds = qlist.split(",");
            // 查询选中的题目，判断是不是单选还是多选
            int single = 0, multi = 0;
            for (String questionId : questionIds) {
                Question question = questionMapper.selectById(Integer.parseInt(questionId));
                // 判断是多选还是单选，并统计其个数
                if (question.getIsmulti()) {
                    multi++;
                } else {
                    single++;
                }
                // 计算总和
                Exam exam2 = examMapper.selectById(recent);
                Double score = exam2.getSelectone() * single + exam2.getSelectmore() * multi;
                // 保存总和
                exam.setScore(score);
            }
        }
        // 更新考题
        int res = examMapper.updateById(exam);
        if (res > 0) {
            result.setCode(0);
            result.setMessage("题目录入成功！");
            // 将学生的考试数据保存到考试记录中
            Integer id = exam.getId();
        } else {
            result.setCode(1);
            result.setMessage("题目录入失败！");
        }
        return result;
    }

    /**
     * 随机组题方法实现
     *
     * @param single   单选题总数
     * @param multiple 多选题总数
     * @param classId  班级id
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
        if (singleCount + multiCount < 10) {
            result.setCode(1);
            result.setMessage("随机组题失败，当前班级题库中的题目数未超过10个。如果仍要组题，请通过自主选题进行组题！");
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
        if (res > 0) {
            result.setCode(0);
            result.setMessage("删除成功！");
        } else {
            result.setCode(1);
            result.setMessage("删除失败，该考试已经删除！");
        }
        return result;
    }

    /**
     * 删除多条考试编号，同时删除考试记录
     *
     * @param examId 考试编号
     * @return 删除状态
     */
    @Override
    public BaseResult deleteExam(Integer[] examId) {
        BaseResult result = new BaseResult();
        // 删除考试表的中考试信息
        int res = examMapper.deleteBatchIds(Arrays.asList(examId));
        // 删除考试记录
        recordMapper.delete(new QueryWrapper<Record>().in("examId", Arrays.asList(examId)));
        if (res > 0) {
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
    public Map<String, Object> showExam(Integer id) throws ExamNotCompleteException {
        Map<String, Object> map = new HashMap<>();
        Exam exam = examMapper.selectById(id);
        // 查询题目集编号
        String qlist = exam.getQlist();
        // 题目数组
        if (qlist != null) {
            ArrayList<Question> questions = new ArrayList<>();
            String[] questionIds = qlist.split(",");
            // 统计单选题和多选题的个数
            int singleCount = 0, multiCount = 0;
            // 添加试卷到问题集中
            for (String questionId : questionIds) {
                // 查询问题
                Question question = questionMapper.selectById(Integer.parseInt(questionId));
                // 判断试卷题目是否完整
                if(question == null) {
                    throw new ExamNotCompleteException("试卷中的试题，有一部分已经不存在，该考试无效");
                }
                // 判断选择题是否为多选，如果是，多选+1，否则单选+1。
                if (question.getIsmulti()) {
                    multiCount++;
                } else {
                    singleCount++;
                }
                questions.add(question);
            }
            // 添加试卷信息
            map.put("questions", questions); // 题目集
            map.put("single", exam.getSelectone()); // 单选分数
            map.put("multi", exam.getSelectmore()); // 多选分数
            map.put("singleCount", singleCount); // 单选总数
            map.put("multiCount", multiCount); // 多选总数
            map.put("score", exam.getScore()); // 总分
            map.put("name", exam.getExamname()); // 考试名称
            map.put("starttime", exam.getStarttime()); // 考试开始时间
            map.put("stoptime", exam.getStoptime()); // 考试结束时间
            map.put("time", DateUtils.timeDistance(exam.getStoptime(), exam.getStarttime())); // 考试时间
        }
        return map;
    }

    /**
     * 判断该学生是否在这个班级中
     *
     * @param stuId  学生id
     * @param examId 考试id
     * @return 在班上？
     */
    @Override
    public boolean checkStudentInClass(Integer stuId, Integer examId) {
        // 查询条件
        QueryWrapper<ExamClass> wrapper = new QueryWrapper<>();
        wrapper.eq("examid", examId);
        ExamClass examClass = examClassMapper.selectOne(wrapper);
        if(examClass == null) {
            throw new RuntimeException("该考试莫名其妙被删除！");
        }
        // 获取当前考试所在的班级
        Integer examClassId = examClass.getClassid();

        // 获取学生所在的班级
        List<Integer> studentClassList = studentClassMapper.findStuIdByClassesId(stuId);
        // 判断是否在班级中
        return studentClassList.contains(examClassId);
    }

    /**
     * 保存答案
     *
     * @param selectOne  单选题答案
     * @param selectMore 多选题答案
     * @param examId     考试id
     * @param stuId      学生id
     * @return 返回保存状态
     */
    @Override
    public BaseResult saveAnswer(String selectOne, String selectMore, Integer examId, Integer stuId) {
        ExamInfo examInfo = new ExamInfo(examId, stuId).invoke();
        BaseResult result = examInfo.getResult();
        Record record1 = examInfo.getRecord1();
        Date stoptime = examInfo.getStoptime();
        if(stoptime == null) {
            result.setCode(1);
            result.setMessage("保存失败，该考试莫名其妙被删除！");
            return result;
        }
        // 如果不存在或者还未到时间
        if (DateUtils.timeDistanceMillionSeconds(stoptime, new Date()) >= -10000) {
            if (record1 == null) {
                // 添加记录实体类
                Record record = new Record();
                record.setExamid(examId);
                record.setStuid(stuId);
                // 单选和多选的结合体
                record.setAnswer(selectOne + "," + selectMore);
                int res = recordMapper.insert(record);
                if (res > 0) {
                    result.setCode(0);
                    result.setMessage("成功保存至服务器");
                } else {
                    result.setCode(1);
                    result.setMessage("保存失败");
                }
            } else {
                // 判断考生是否已经交卷或者时间到了
                if (record1.getScore() == null) {
                    // 否则，更新答题信息
                    record1.setAnswer(selectOne + "," + selectMore);
                    int res = recordMapper.updateById(record1);
                    if (res > 0) {
                        result.setCode(0);
                        result.setMessage("成功保存至服务器");
                    } else {
                        result.setCode(1);
                        result.setMessage("保存失败");
                    }
                }
            }
        } else {
            result.setCode(1);
            result.setMessage("你的考试已经结束，无法再保存试卷了！");
            return result;
        }

        return result;
    }

    /**
     * 提交试卷并批改
     *
     * @param selectOne  单选题答案
     * @param selectMore 多选题答案
     * @param examId     考试id
     * @param stuId      学生id
     */
    @Override
    public BaseResult commitAnswer(String selectOne, String selectMore, Integer examId, Integer stuId) {
        BaseResult result = new BaseResult();
        // 查询当前考生记录是否存在
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("examid", examId);
        queryWrapper.eq("stuid", stuId);
        Record record1 = recordMapper.selectOne(queryWrapper);
        Exam exam = examMapper.selectById(examId);
        if(exam == null) {
            result.setCode(1);
            result.setMessage("提交失败，该考试莫名其妙被删除");
            return result;
        }
        // 获取考试时间
        Date stoptime = exam.getStoptime();
        // 判断考试是否结束
        if (DateUtils.timeDistanceMillionSeconds(stoptime, new Date()) >= -10000) {
            // 如果查询不到数据，则添加数据
            if (record1 == null) {
                // 添加记录实体类
                record1 = new Record();
                record1.setExamid(examId);
                record1.setStuid(stuId);
                // 判断单选题是否为空
                if (selectOne != null && selectMore != null) {
                    // 设置单选题
                    record1.setAnswer(selectOne + "," + selectMore);
                } else if (selectOne != null) {
                    record1.setAnswer(selectOne);
                } else if (selectMore != null) {
                    record1.setAnswer(selectMore);
                }
                // 添加考试记录
                recordMapper.insert(record1);
                // 再查一次，得到id
                record1 = recordMapper.selectOne(queryWrapper);
            }
            // 通过得分判断学生是否做了试卷
            if (record1.getScore() == null) {
                if (record1.getAnswer() != null) {
                    //---- 开始批改
                    // 再次判断是否交白卷
                    String[] answers = record1.getAnswer().split(",");
                    // 声明正确的题号集合和错误的题号集合
                    ArrayList<String> rights = new ArrayList<>(), mistakes = new ArrayList<>();
                    // 获取本次考试的题号
                    String[] questionIds = exam.getQlist().split(",");
                    // 总分
                    double score = 0;
                    // 遍历此次考试题号
                    for (int i = 0; i < questionIds.length; i++) {
                        Integer questionId = Integer.parseInt(questionIds[i]);
                        // 查询题目
                        Question question = questionMapper.selectById(questionId);
                        // 获取当前题目对应的答案
                        String answer = question.getAnswer();
                        // 如果答案正确，放入正确的题号集中，并统计正确
                        if (answers[i].equals(answer)) {
                            rights.add(questionIds[i]);
                            // 统计分数
                            score += (question.getIsmulti() ? exam.getSelectmore() : exam.getSelectone());
                        } else {
                            // 答案错误，则放在错误的题号集
                            mistakes.add(questionIds[i]);
                        }
                    }
                    // 设置得分
                    record1.setScore(score);
                    // 正确的题目
                    String correct = StringUtils.join(rights, ",");
                    record1.setCorrect(correct);
                    // 错误的题目
                    String wrong = StringUtils.join(mistakes, ",");
                    record1.setWrong(wrong);
                } else {
                    // 0分
                    record1.setScore(0.0);
                }
            } else {
                result.setCode(1);
                result.setMessage("你的考试已经结束，无法再次交卷了！");
                return result;
            }
        } else {
            result.setCode(1);
            result.setMessage("你的考试已经结束，无法再次交卷了！");
            return result;
        }
        // 保存改卷信息
        int res = recordMapper.updateById(record1);
        if (res > 0) {
            result.setCode(0);
            result.setMessage("试卷提交成功，无法再次答题了！");
        } else {
            result.setCode(1);
            result.setMessage("试卷提交失败！");
        }
        return result;
    }

    /**
     * 查询当前学生对应的答案
     *
     * @param examId 考试id
     * @param stuId  学生id
     */
    @Override
    public void findAnswer(Integer examId, Integer stuId, Model model) {
        // 查询当前考生记录是否存在
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("examid", examId);
        queryWrapper.eq("stuid", stuId);
        // 查询记录
        Record record = recordMapper.selectOne(queryWrapper);
        if (record != null) {
            // 找出答案
            model.addAttribute("answer", record.getAnswer().split(","));
        }
    }

    /**
     * 查询当前学生对应的答案
     *
     * @param examId 考试id
     * @param stuId  学生id
     */
    @Override
    public void showAnswer(Integer examId, Integer stuId, Model model) {
        // 判断考生是否交卷或者时间是否到了
        ExamInfo examInfo = new ExamInfo(examId, stuId).invoke();
        Date stoptime = examInfo.stoptime;
        if(stoptime == null) {
            model.addAttribute("msg", "该考试莫名其妙被删除！");
            return;
        }
        // 如果考试时间到了或者已经交卷了
        if (examInfo.record1 != null) {
            if (DateUtils.timeDistanceMillionSeconds(stoptime, new Date()) < -10000 || examInfo.record1.getScore() != null) {
                // 查询记录
                Record record = examInfo.getRecord1();
                if (record != null) {
                    // 加载正确题号
                    String correct1 = record.getCorrect();
                    // 判断正确题号是否为空
                    if(correct1 == null) {
                        correct1 = "";
                    }
                    String[] correct = correct1.split(",");
                    Integer[] convert = (Integer[]) ConvertUtils.convert(correct, Integer.class);
                    model.addAttribute("correct", Arrays.asList(convert));
                    // 得分
                    model.addAttribute("get_score", record.getScore());
                }
            }
        }
    }
    /**
     * 查询学生的考试成绩
     *
     * @param studentId 学生id2
     * @return 返回状态和成绩数据
     */
    @Override
    public MultipleDataResult<Map<String, Object>> findExamPassed(Integer studentId) {
        // 创建一个列表，用户保存查询到的成绩和考试信息
        List<Map<String, Object>> data = new ArrayList<>();
        // 查询成绩
        List<Record> examList=recordMapper.findExamPassed(studentId);
        // 遍历出查询到的成绩
        for (int i=0;i<examList.size();i++){
            // 创建 Map 对象，保存成绩和考试信息
            Map<String, Object> map=new HashMap<>();
            Record record=examList.get(i);
            Exam exam=examMapper.findExam(record.getExamid());
            map.put("examcode", record.getExamid());
            map.put("examname",exam.getExamname());
            map.put("totalscore", exam.getScore());
            map.put("myscore",record.getScore());
            map.put("starttime",exam.getStarttime());
            map.put("stoptime",exam.getStoptime());
//            map.put("classcount", classesMapper.findClassesCount(classes.getId()));
            // 添加到 list 里面
            data.add(map);
        }

        // 返回数据
        MultipleDataResult<Map<String, Object>> dataResult = new MultipleDataResult<>();

        dataResult.setCode(0);
        dataResult.setData(data);
        dataResult.setTotal(examList.size());
        return dataResult;
    }

    /**
     *
     * 通过学生id找考试 id
     * @param stuId
     * @return
     */

    @Override
    public int findExamId(Integer stuId) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stuid", stuId);
        // 查询记录
        Record record = recordMapper.selectOne(queryWrapper);
        return record.getExamid();

    }

    /**
     * 输出考试成绩
     *
     * @param examId 考试id
     * @param model  视图层模型
     * @param desc 是否逆序  1-是，0-不是
     */
    @Override
    public void printExamScore(Integer classId, Integer examId, Model model, Integer desc) {
        // 查询考试信息
        Exam exam = examMapper.selectById(examId);
        // 查询成绩
        List<GradeTable> gradeTables = recordMapper.getRecordByClassId(classId, examId, desc);
        // 总分
        double totalScore = 0;
        // 已考人数
        int examCount = 0;
        // 循环遍历考试记录
        for (GradeTable record : gradeTables) {
            // 判断是否已考，但未交卷
            if (record.getExamId() != null &&
                    DateUtils.timeDistance(exam.getStoptime(), new Date()) >= 0 && record.getScore() == null) {
                record.setStatus("考试中");
            }
            // 判断已交卷
            else if (record.getExamId() != null && record.getScore() != null) {
                record.setStatus("已交卷");
                totalScore += record.getScore();
                examCount++;
            }
            // 未考
            else if (record.getExamId() == null && DateUtils.timeDistanceMillionSeconds(exam.getStoptime(), new Date()) >= 0) {
                record.setStatus("未考");
            }
            // 缺考
            else {
                record.setStatus("缺考");
            }
        }
        model.addAttribute("students", gradeTables);
        model.addAttribute("exam", exam);
        // 仅已考平均分
        double d = totalScore / examCount;
        model.addAttribute("avg1", Double.isNaN(d) ? "" : String.format("%.2f", d));
    }

    /**
     * 公共部分抽取出来
     */
    private class ExamInfo {

        private Integer examId;
        private Integer stuId;
        private BaseResult result;
        private Record record1;
        private Date stoptime;
        private Exam exam;

        public ExamInfo(Integer examId, Integer stuId) {
            this.examId = examId;
            this.stuId = stuId;
        }

        public BaseResult getResult() {
            return result;
        }

        public Record getRecord1() {
            return record1;
        }

        public Date getStoptime() {
            return stoptime;
        }

        public ExamInfo invoke() {
            result = new BaseResult();
            // 查询当前考生记录是否存在
            QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("examid", examId);
            queryWrapper.eq("stuid", stuId);
            record1 = recordMapper.selectOne(queryWrapper);
            exam = examMapper.selectById(examId);
            // 获取考试时间
            stoptime = exam == null ? null : exam.getStoptime();
            return this;
        }
    }
}
