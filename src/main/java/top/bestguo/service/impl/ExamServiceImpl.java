package top.bestguo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.bestguo.entity.Classes;
import top.bestguo.entity.Exam;
import top.bestguo.entity.ExamClass;
import top.bestguo.entity.Student;
import top.bestguo.mapper.ClassesMapper;
import top.bestguo.mapper.ExamClassMapper;
import top.bestguo.mapper.ExamMapper;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.service.ExamService;

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
}
