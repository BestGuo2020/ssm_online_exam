package top.bestguo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bestguo.entity.Exam;
import top.bestguo.entity.Student;
import top.bestguo.mapper.ExamMapper;
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
}
