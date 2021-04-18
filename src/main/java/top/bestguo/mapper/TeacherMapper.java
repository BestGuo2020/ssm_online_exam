package top.bestguo.mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;
import top.bestguo.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity top.bestguo.entity.Teacher
 */
@Repository
public interface TeacherMapper extends BaseMapper<Teacher> {

    Teacher findOneByEmail(@Param("email") String email);

}




