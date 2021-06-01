package top.bestguo.mapper;

import org.apache.ibatis.annotations.Param;
import top.bestguo.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

}




