package top.bestguo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bestguo.mapper.IDemoMapper;
import top.bestguo.service.IDemoService;

@Service("demoService")
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private IDemoMapper demoMapper;

    @Override
    public void findAllService() {
        System.out.println(demoMapper.findAll());
    }
}
