package top.bestguo.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.bestguo.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity top.bestguo.entity.Student
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {
  /*
  * 学生注册
  * */
  int addStudent(Student student);
  Student findStudent(@Param("email")String email,@Param("password")String password);

}




