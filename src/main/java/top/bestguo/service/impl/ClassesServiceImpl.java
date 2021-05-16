package top.bestguo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bestguo.entity.Classes;
import top.bestguo.mapper.ClassesMapper;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.render.SingleDataResult;
import top.bestguo.service.ClassesService;
import top.bestguo.util.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * 查询当前老师所在的班级信息
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
        // 返回数据
        MultipleDataResult<Classes> dataResult = new MultipleDataResult<>();
        if(classesList.size() > 0) {
            dataResult.setCode(0);
        }
        dataResult.setData(classesList);
        dataResult.setTotal(classesList.size());
        return dataResult;
    }
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
            map.put("classcount", classesMapper.findClassesCount(classes.getId()));
            // 添加到 list 里面
            data.add(map);
        }

        // 返回数据
        MultipleDataResult<Map<String, Object>> dataResult = new MultipleDataResult<>();

        if(classesList.size() > 0) {
            dataResult.setCode(0);
        }
        dataResult.setData(data);
        dataResult.setTotal(classesList.size());
        return dataResult;
    }
    /**
     * 学生退出班级
     *
     * @param studentId 学生id
     * @param  classesId 课堂id
     * @return 状态
     */
    @Override
    public BaseResult deleteJoinClass(Integer studentId, Integer classesId) {
        BaseResult result = new BaseResult();

        int res = classesMapper.deleteJoinClass(studentId,classesId);
        System.out.println(res );
        if(res > 0) {
            result.setCode(0);
            result.setMessage("班级退出成功");
        } else {
            result.setCode(1);
            result.setMessage("班级退出失败");
        }
        return result;
    }


}
