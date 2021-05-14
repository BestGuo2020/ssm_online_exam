package top.bestguo.mapper;

import top.bestguo.entity.Classes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

}




