package top.bestguo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.bestguo.entity.Classes;
import top.bestguo.entity.Student;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.render.SingleDataResult;
import top.bestguo.service.ClassesService;
import top.bestguo.service.StudentService;

import java.util.Map;

/**
 * 班级管理控制层
 */
@Controller
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;
    @Autowired
    private StudentService studentService;

    /**
     * 班级信息的基本增删改请求处理
     *
     * @param select 增删改功能选择：1-添加、2-修改、3-删除
     * @param classes 班级实体类
     * @return 返回增删改的状态
     */
    @RequestMapping(value = "/classManager_do/{select}", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult classManager_do(@PathVariable Integer select, Classes classes) {
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
     * 班级信息删除多个
     *
     * @param teacherId 教师id
     * @param classesId 班级实体类
     * @return 返回增删改的状态
     */
    @RequestMapping(value = "/classManager_delMany", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult classManager_do(Integer teacherId, Integer[] classesId) {
        return classesService.deleteClass(teacherId, classesId);
    }

    /**
     * 查询当前教师拥有的班级
     *
     * @param teacherId 教师id
     * @param page 当前页数
     * @param limit 当前页面
     * @return
     */
    @RequestMapping(value = "/loadAllClasses/{teacherId}")
    @ResponseBody
    public MultipleDataResult<Classes> loadAllClasses(@PathVariable Integer teacherId,
                                                      @RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer limit) {
        // 判断分页传来的参数是否为空
        if(page == null || limit == null)
            return classesService.findAllClass(teacherId);
        else
            return classesService.findAllClass(teacherId, page, limit);
    }

    /**
     * 通过班级id查询当前班级的学生信息
     *
     * @param classId 班级号
     * @param page 当前页
     * @param limit 当前页展示的数据条数
     * @return 学生信息
     */
    @RequestMapping(value = "/loadStudentByClass/{classId}")
    @ResponseBody
    public MultipleDataResult<Student> loadStudentByClass(@PathVariable Integer classId,
                                                          @RequestParam Integer page,
                                                          @RequestParam Integer limit) {

        return studentService.selectStudentByClassId(classId, page, limit);

    }

    /**
     * 从班级踢出学生
     *
     * @param classId 班级id
     * @param stuId 学生id
     * @return 返回状态
     */
    @RequestMapping(value = "/kickOut")
    @ResponseBody
    public BaseResult kickOutStudent(@RequestParam Integer classId, @RequestParam Integer stuId){
        return classesService.kickOutStudent(classId, stuId);
    }

    /**
     * 从班级踢出学生
     *
     * @param classId 班级id
     * @param stuIds 多个学生id
     * @return 返回状态
     */
    @RequestMapping(value = "/kickOutMany")
    @ResponseBody
    public BaseResult kickOutStudent(@RequestParam Integer classId, @RequestParam Integer[] stuIds){
        return classesService.kickOutStudent(classId, stuIds);
    }

    /**
     * 查询当前学生拥有的班级
     *
     * @param studentId
     * @return
     */
    @RequestMapping(value = "/loadJoinedClasses/{studentId}")
    @ResponseBody
    public MultipleDataResult<?> loadJoinedClasses(@PathVariable Integer studentId) {

        return classesService.findJoinClass(studentId);
    }
    /**
     * 学生退出班级请求处理
     * @param studentId 学生ID
     * @param classes 班级实体类
     * @return 返回删除的状态
     */
    @RequestMapping(value = "/deleteClass_do/{studentId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteJoinClass( @PathVariable Integer studentId ,Classes classes) {

            return classesService.deleteJoinClass(studentId,classes.getId());


    }
    /**
     * 学生退出多个班级
     *
     * @param classIds 多个班级id
     * @param stuId 学生id
     * @return 返回状态
     */
    @RequestMapping(value = "/deleteClassMany/{stuId}")
    @ResponseBody
    public BaseResult deleteJoinClass(@PathVariable Integer stuId ,Integer[] classIds){
        return classesService.deleteJoinClass(stuId,classIds);
    }

}
