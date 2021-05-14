package top.bestguo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.bestguo.entity.Classes;
import top.bestguo.mapper.ClassesMapper;

/**
 * 测试 Mybatis-Plus 忽略简单的单表操作
 */
public class MybatisPlusTest {

    /**
     * 查询的操作
     */
    @Test
    public void testSelect() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        // 得到班级
        ClassesMapper classesMapper = (ClassesMapper) applicationContext.getBean("classesMapper");
        // 通过 id 查询
        Classes classes = classesMapper.selectById(1);

        System.out.println(classes);
    }

    /**
     * 添加的操作
     */
    @Test
    public void testAdd() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        // 得到班级
        ClassesMapper classesMapper = (ClassesMapper) applicationContext.getBean("classesMapper");
        // 加载数据
        Classes classes = new Classes();
        classes.setClasscode("284762");
        classes.setClassdesc("会计学原理 - 陈昌");
        classes.setClassname("会计学原理");
        // 插入
        int res = classesMapper.insert(classes);

        System.out.println(res > 0 ? "插入成功" : "插入失败");
    }

    /**
     * 修改的操作
     */
    @Test
    public void testModify() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        // 得到班级
        ClassesMapper classesMapper = (ClassesMapper) applicationContext.getBean("classesMapper");
        // 加载数据
        Classes classes = new Classes();
        classes.setId(1);
        classes.setClassdesc("会计学原理 - 陈昌 - 软件工程（专升本）4班");
        // 插入
        int res = classesMapper.updateById(classes);

        System.out.println(res > 0 ? "修改成功" : "修改失败");
    }

}
