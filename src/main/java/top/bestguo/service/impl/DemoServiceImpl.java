package top.bestguo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bestguo.dao.IDemoDao;
import top.bestguo.service.IDemoService;

@Service("demoService")
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private IDemoDao demoDao;

    @Override
    public void findAllService() {
        System.out.println(demoDao.findAll());
    }
}
