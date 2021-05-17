package top.bestguo.mapper;

import org.apache.ibatis.annotations.Param;
import top.bestguo.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity top.bestguo.entity.Question
 */
public interface QuestionMapper extends BaseMapper<Question> {

    Boolean insertBatch(@Param("questions") List<Question> questions);

}




