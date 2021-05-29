package top.bestguo.mapper;

import org.springframework.stereotype.Repository;
import top.bestguo.entity.StudentClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity top.bestguo.entity.StudentClass
 */
@Repository
public interface StudentClassMapper extends BaseMapper<StudentClass> {

    int joinClass(StudentClass studentClass);

    List<Integer> findStuIdByClassesId(Integer stuId);
}




