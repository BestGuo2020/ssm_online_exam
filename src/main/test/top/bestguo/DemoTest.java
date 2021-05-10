package top.bestguo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.bestguo.mapper.TeacherMapper;
import top.bestguo.service.IDemoService;

public class DemoTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        /*IDemoService demoDao = (IDemoService) applicationContext.getBean("demoService");

        demoDao.findAllService();*/

        TeacherMapper teacherMapper = (TeacherMapper) applicationContext.getBean("teacherMapper");
        System.out.println(teacherMapper.findOneByEmail("111@qq.com"));
    }

    @Test
    public void test2() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        IDemoService demoDao = (IDemoService) applicationContext.getBean("demoService");

        demoDao.findAllService();
    }

}
