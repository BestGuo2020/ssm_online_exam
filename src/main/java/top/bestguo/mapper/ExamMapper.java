package top.bestguo.mapper;

import org.apache.ibatis.annotations.Param;
import top.bestguo.entity.Exam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity top.bestguo.entity.Exam
 */
public interface ExamMapper extends BaseMapper<Exam> {

    /**
     * 通过班级id查询当前班级的考试信息
     *
     * @param classId 班级id
     * @return 考试信息实体类
     */
    List<Exam> findExamInfoByClassId(@Param("classId") Integer classId);

    /**
     * 查询最近查询的考试id信息
     * @return
     */
    Integer findExamRecent();
    /**
     * 查询考试信息
     *
     * @param  examId  考试id
     * @return 考试信息
     */
    Exam findExam(@Param("id") Integer examId);
}




