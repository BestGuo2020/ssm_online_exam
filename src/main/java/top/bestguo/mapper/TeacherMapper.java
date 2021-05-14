package top.bestguo.mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;
import top.bestguo.entity.Student;
import top.bestguo.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity top.bestguo.entity.Teacher
 */
@Repository
public interface TeacherMapper extends BaseMapper<Teacher> {

    Teacher findOneByEmail(@Param("email") String email);
    Teacher findTeacher(@Param("email")String email, @Param("password")String password);
    int addTeacher(Teacher teacher);
}




