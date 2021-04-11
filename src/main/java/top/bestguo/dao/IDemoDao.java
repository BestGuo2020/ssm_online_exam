package top.bestguo.dao;

import org.springframework.stereotype.Repository;
import top.bestguo.entity.Demo;

import java.util.List;

@Repository("demoDao")
public interface IDemoDao {

    List<Demo> findAll();

}
