package top.bestguo.mapper;

import org.apache.ibatis.annotations.Param;

import top.bestguo.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.bestguo.vo.GradeTable;

import java.util.List;

import java.util.List;

/**
 * @Entity top.bestguo.entity.Record
 */
public interface RecordMapper extends BaseMapper<Record> {
    /**
     * 查询学生的考试成绩
     *
     * @return 返回学生的考试成绩
     */
    List<Record> findExamPassed(@Param("stuId") Integer stuId);

    /**
     * 通过班级id、试卷id查询学生成绩，并且对学生成绩进行排序
     *
     * @param classId 班级id
     * @param examId 考生id
     * @param desc 正逆序？1-正序、2-逆序
     * @return 考生的成绩信息
     */
    List<GradeTable> getRecordByClassId(@Param("classId") Integer classId,
                                        @Param("examId") Integer examId,
                                        @Param("desc") Integer desc);

}




