package top.bestguo.mapper;

import org.apache.ibatis.annotations.Param;
import top.bestguo.entity.Classes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.bestguo.render.BaseResult;

import java.util.List;

/**
 * @Entity top.bestguo.entity.Classes
 */
public interface ClassesMapper extends BaseMapper<Classes> {


    /**
     * 查询所有的班级码
     *
     * @return 返回班级码
     */
    List<String> findAllClassCode();
    /**
     * 查询学生所加入的班级
     *
     * @return 返回学生加入的班级
     */
   List<Classes>findJoinClasses(@Param("stuId") Integer stuId);
    /**
     * 查询学生加入的班级人数
     *
     * @param classId 课程id
     * @return 班级人数
     */
     Integer findClassesCount(@Param("classId") Integer classId);
     /**
     * 学生退出班级处理
     *
     * @param studentId 学生id
     * @param classId 课程id
     * @return 数据
     */
    Integer deleteJoinClass(@Param("stuId") Integer studentId, @Param("classId")Integer classId);
}




