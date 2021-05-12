package top.bestguo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.bestguo.entity.Classes;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.render.SingleDataResult;
import top.bestguo.service.ClassesService;

/**
 * 班级管理控制层
 */
@Controller
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    /**
     * 班级信息的增删改请求处理
     *
     * @param select 增删改功能选择：1-添加、2-修改、3-删除
     * @param classes 班级实体类
     * @return 返回增删改的状态
     */
    @RequestMapping(value = "/classManager_do/{select}", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult classManager_do(@PathVariable("select") Integer select, Classes classes) {
        if(select == 1) {
            return classesService.createClass(classes);
        } else if(select == 2) {
            return classesService.modifyClass(classes);
        } else if(select == 3) {
            return classesService.deleteClass(classes.getBelongteacher(), classes.getId());
        }
        BaseResult result = new BaseResult();
        result.setMessage("没有这个功能");
        result.setCode(1);
        return result;
    }

    /**
     * 查询当前教师拥有的班级
     *
     * @param teacherId
     * @return
     */
    @RequestMapping(value = "/loadAllClasses/{teacherId}")
    @ResponseBody
    public MultipleDataResult<Classes> loadAllClasses(@PathVariable Integer teacherId) {
        return classesService.findAllClass(teacherId);
    }

}
