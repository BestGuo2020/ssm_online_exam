package top.bestguo.mapper;

import org.springframework.stereotype.Repository;
import top.bestguo.entity.Demo;

import java.util.List;

@Repository
public interface DemoMapper {

    List<Demo> findAll();

}
