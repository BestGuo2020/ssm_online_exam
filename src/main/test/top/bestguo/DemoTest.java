package top.bestguo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.bestguo.dao.IDemoDao;
import top.bestguo.service.IDemoService;

public class DemoTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        IDemoService demoDao = (IDemoService) applicationContext.getBean("demoService");

        demoDao.findAllService();
    }

}
