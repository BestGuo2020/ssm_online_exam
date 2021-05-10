package top.bestguo.mapper;

import org.springframework.stereotype.Repository;
import top.bestguo.entity.Demo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity top.bestguo.entity.Demo
 */
@Repository("demoMapper")
public interface IDemoMapper extends BaseMapper<Demo> {

    List<Demo> findAll();

}




