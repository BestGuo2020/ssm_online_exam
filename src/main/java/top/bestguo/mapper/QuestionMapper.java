package top.bestguo.mapper;

import org.apache.ibatis.annotations.Param;
import top.bestguo.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity top.bestguo.entity.Question
 */
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 批量插入
     *
     * @param questions 问题实体类
     * @return true or false
     */
    Boolean insertBatch(@Param("questions") List<Question> questions);

    /**
     * 查询问题解析
     *
     * @param questionId 问题编号
     * @return 解析
     */
    List<String> selectReasonByIds(@Param("questionId") int[] questionId);

}




