package top.bestguo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.bestguo.entity.Classes;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.service.ClassesService;

import java.util.List;

public class ServicesTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ClassesService classesService = (ClassesService) applicationContext.getBean("classService");
        MultipleDataResult<Classes> allClass = classesService.findAllClass(1, 1, 2);
        List<Classes> classData = allClass.getData();
        System.out.println(classData);
    }

}
