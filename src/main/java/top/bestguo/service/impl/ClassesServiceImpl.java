package top.bestguo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bestguo.entity.Classes;
import top.bestguo.entity.StudentClass;
import top.bestguo.mapper.ClassesMapper;
import top.bestguo.mapper.StudentClassMapper;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.render.SingleDataResult;
import top.bestguo.service.ClassesService;
import top.bestguo.util.RandomUtils;


import java.util.ArrayList;
import java.util.HashMap;

import java.util.Arrays;

import java.util.List;
import java.util.Map;

/**
 * 班级相关的服务实现类
 *
 * 教师端：对班级进行创建、修改、清除、查询
 * 学生端：加入班级，查询已加入的班级，退出班级
 */
@Service("classService")
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private StudentClassMapper studentClassMapper;

    /**
     * 创建班级
     *
     * @param classes 班级实体类
     * @return 返回状态
     */
    @Override
    public BaseResult createClass(Classes classes) {
        BaseResult result = new BaseResult();
        // 查询所有加课码
        List<String> classCodes = classesMapper.findAllClassCode();
        // 生成加课码
        String classCode = RandomUtils.generateCodeLength8().toString();
        // 判断加课码是否有重复
        while(classCodes.contains(classCode)){
            classCode = RandomUtils.generateCodeLength8().toString();
        }
        // 添加到实体类中
        classes.setClasscode(classCode);
        // 插入数据
        int res = classesMapper.insert(classes);
        if(res > 0) {
            result.setCode(0);
            result.setMessage("班级创建成功");
        } else {
            result.setCode(1);
            result.setMessage("班级创建失败");
        }
        return result;
    }

    /**
     * 修改班级
     *
     * @param classes 班级实体类
     * @return 返回状态
     */
    @Override
    public BaseResult modifyClass(Classes classes) {
        BaseResult result = new BaseResult();
        // 更新数据
        int res = classesMapper.updateById(classes);
        if(res > 0) {
            result.setCode(0);
            result.setMessage("班级修改成功");
        } else {
            result.setCode(1);
            result.setMessage("班级修改失败");
        }
        return result;
    }

    /**
     * 删除班级
     *
     * @param teacherId 教师id
     * @param classesId 班级id
     * @return 返回状态
     */
    @Override
    public BaseResult deleteClass(Integer teacherId, Integer classesId) {
        BaseResult result = new BaseResult();
        // 设置查询条件
        QueryWrapper<Classes> wrapper = new QueryWrapper<>();
        wrapper.eq("belongteacher", teacherId);
        wrapper.eq("id", classesId);
        // 删除数据
        int res = classesMapper.delete(wrapper);
        if(res > 0) {
            result.setCode(0);
            result.setMessage("班级删除成功");
        } else {
            result.setCode(1);
            result.setMessage("班级删除失败");
        }
        return result;
    }

    /**
     * 删除多个班级
     *
     * @param teacherId 教师id
     * @param classesId 多个班级id
     * @return 返回状态
     */
    @Override
    public BaseResult deleteClass(Integer teacherId, Integer[] classesId) {
        BaseResult result = new BaseResult();
        // 设置查询条件
        QueryWrapper<Classes> wrapper = new QueryWrapper<>();
        wrapper.eq("belongteacher", teacherId);
        wrapper.in("id", Arrays.asList(classesId));
        // 删除数据
        int res = classesMapper.delete(wrapper);
        if(res > 0) {
            result.setCode(0);
            result.setMessage("删除多个班级成功");
        } else {
            result.setCode(1);
            result.setMessage("删除多个班级失败");
        }
        return result;
    }

    /**
     * 查询单个班级信息
     *
     * @param classes 班级
     * @return
     */
    @Override
    public SingleDataResult<Classes> findOneClass(Classes classes) {
        SingleDataResult<Classes> result = new SingleDataResult<>();
        // 设置查询条件
        QueryWrapper<Classes> wrapper = new QueryWrapper<>();
        wrapper.eq("belongteacher", classes.getBelongteacher());
        wrapper.eq("id", classes.getId());
        // 查询数据
        Classes data = classesMapper.selectOne(wrapper);
        if(data != null) {
            result.setCode(0);
            result.setData(data);
        } else {
            result.setCode(1);
            result.setMessage("没有查询到相关数据");
        }
        return result;
    }

    @Override
    public Classes findOneClassByClassCode(Integer classcode) {
        // 查询条件
        QueryWrapper<Classes> classesQueryWrapper = new QueryWrapper<>();
        classesQueryWrapper.eq("classcode",classcode);
        Classes classes = classesMapper.selectOne(classesQueryWrapper);
        return classes;
    }

    @Override
    public Boolean isStudentAtTheClass(Integer classId, Integer stuId) {
        QueryWrapper<StudentClass> classesQueryWrapper = new QueryWrapper<>();
        classesQueryWrapper.eq("classid", classId);
        classesQueryWrapper.eq("stuid", stuId);
        StudentClass studentClass = studentClassMapper.selectOne(classesQueryWrapper);
        return studentClass != null;
    }

    /**
     * 查询当前老师所管理的班级
     *
     * @param teacherId 教师id
     * @return 所有的班级信息
     */
    @Override
    public MultipleDataResult<Classes> findAllClass(Integer teacherId) {
        // 设置查询条件
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belongteacher", teacherId);
        // 开始查询
        List<Classes> classesList = classesMapper.selectList(queryWrapper);
        return getClassesMultipleDataResult(classesList, null);
    }

    /**
     * 查询当前老师所管理的班级信息
     *
     * @param teacherId 教师id
     * @param p 当前页
     * @param limit 当前页展示的数据条数
     * @return 当前页的班级信息
     */
    public MultipleDataResult<Classes> findAllClass(Integer teacherId, Integer p, Integer limit) {
        // 设置查询条件
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belongteacher", teacherId);
        // 设置分页器
        Page<Classes> page = new Page<>(p, limit);
        // 开始查询
        List<Classes> classesList = classesMapper.selectPage(page, queryWrapper).getRecords();
        return getClassesMultipleDataResult(classesList, page);
    }

    /**
     * 从班级踢出学生
     *
     * @param classId 班级id
     * @param stuId 学生id
     * @return 返回状态
     */
    @Override
    public BaseResult kickOutStudent(Integer classId, Integer stuId) {
        BaseResult result = new BaseResult();
        // 设置查询条件
        QueryWrapper<StudentClass> wrapper = new QueryWrapper<>();
        wrapper.eq("classid", classId);
        wrapper.eq("stuid", stuId);
        // 删除数据
        int res = studentClassMapper.delete(wrapper);
        if(res > 0) {
            result.setCode(0);
            result.setMessage("踢出学生成功");
        } else {
            result.setCode(1);
            result.setMessage("踢出学生失败，该学生不在班级中");
        }

        return result;
    }

    /**
     * 从班级踢出学生
     *
     * @param classId 班级id
     * @param stuIds 多个学生id
     * @return 返回状态
     */
    @Override
    public BaseResult kickOutStudent(Integer classId, Integer[] stuIds) {
        BaseResult result = new BaseResult();
        // 设置查询条件
        QueryWrapper<StudentClass> wrapper = new QueryWrapper<>();
        wrapper.eq("classid", classId);
        wrapper.in("stuid", Arrays.asList(stuIds));
        // 删除数据
        int res = studentClassMapper.delete(wrapper);
        if(res > 0) {
            result.setCode(0);
            result.setMessage("踢出多个学生成功");
        } else {
            result.setCode(1);
            result.setMessage("踢出多个学生失败，该学生不在班级中");
        }

        return result;
    }

    /**
     * 单独封装的返回结果
     *
     * @param list 查询到的数据
     * @param page 分页器
     * @return 当前页的班级信息或者所有的班级信息
     */
    private MultipleDataResult<Classes> getClassesMultipleDataResult(List<Classes> list, Page<Classes> page) {
        // 返回数据
        MultipleDataResult<Classes> dataResult = new MultipleDataResult<>();
        // 设置数据
        dataResult.setData(list);
        dataResult.setCode(0);
        // 判断是否传入分页器参数
        if(page != null) {
            dataResult.setTotal((int) page.getTotal());
        }
        else {
            dataResult.setTotal(list.size());
        }
        return dataResult;
    }


    /***                    以下都是学生操作班级方法                 ***/


    /**
     * 查询学生所加入的班级
     *
     * @param studentId 学生id
     * @return 学生 加入的班级信息
     */

    @Override
    public MultipleDataResult<Map<String, Object>> findJoinClass(Integer studentId) {

        // 创建一个列表，用户保存查询到的班级信息和班级人数
        List<Map<String, Object>> data = new ArrayList<>();
        // 查询班级
        List<Classes> classesList=classesMapper.findJoinClasses(studentId);
        // 遍历出查询到的班级
        for (int i=0;i<classesList.size();i++){
            // 创建 Map 对象，保存班级信息和班级人数
            Map<String, Object> map=new HashMap<>();
            Classes classes = classesList.get(i);
            map.put("classcode", classes.getClasscode());
            map.put("classname", classes.getClassname());
            map.put("classdesc", classes.getClassdesc());
            map.put("id",classes.getId());
            map.put("classcount", classesMapper.findClassesCount(classes.getId()));
            // 添加到 list 里面
            data.add(map);
        }

        // 返回数据
        MultipleDataResult<Map<String, Object>> dataResult = new MultipleDataResult<>();

        dataResult.setCode(0);
        dataResult.setData(data);
        dataResult.setTotal(classesList.size());
        return dataResult;
    }
    /**
     * 学生退出班级
     *
     * @param stuId 学生id
     * @param  classId 班级id
     * @return 返回状态
     */
    @Override
    public BaseResult deleteJoinClass(Integer stuId, Integer classId) {
        BaseResult result = new BaseResult();
        // 设置查询条件
        QueryWrapper<StudentClass> wrapper = new QueryWrapper<>();
        wrapper.eq("classid", classId);
        wrapper.eq("stuid", stuId);
        // 删除数据
        int res = studentClassMapper.delete(wrapper);
        if(res > 0) {
            result.setCode(0);
            result.setMessage("学生退出成功");
        } else {
            result.setCode(1);
            result.setMessage("学生退出失败，该学生不在班级中");
        }

        return result;
    }
    /**
     * 学生退出多个班级
     *
     * @param stuId 学生id
     * @param  classIds 多个班级id
     * @return 返回状态
     */
    @Override
    public BaseResult deleteJoinClass(Integer stuId, Integer[] classIds) {
        BaseResult result = new BaseResult();
        // 设置查询条件
        QueryWrapper<StudentClass> wrapper = new QueryWrapper<>();
        wrapper.in("classid", Arrays.asList(classIds));
        wrapper.eq("stuid",stuId);
        // 删除数据
        System.out.println(classIds);
        System.out.println(stuId);

        int res = studentClassMapper.delete(wrapper);
        System.out.println(res);
        if(res > 0) {
            result.setCode(0);
            result.setMessage("学生退出成功");
        } else {
            result.setCode(1);
            result.setMessage("学生退出失败，该学生不在班级中");
        }

        return result;
    }


}
