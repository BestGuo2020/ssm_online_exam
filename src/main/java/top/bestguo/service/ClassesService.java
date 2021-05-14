package top.bestguo.service;

import top.bestguo.entity.Classes;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.render.SingleDataResult;

/**
 * 班级相关的服务
 *
 * 教师端：对班级进行创建、修改、清除、查询
 * 学生端：加入班级，查询已加入的班级，退出班级
 */
public interface ClassesService {

    /**
     * 创建班级
     *
     * @param classes 班级实体类
     * @return 返回状态
     */
    BaseResult createClass(Classes classes);

    /**
     * 修改班级
     *
     * @param classes 班级实体类
     * @return 返回状态
     */
    BaseResult modifyClass(Classes classes);

    /**
     * 删除班级
     *
     * @param teacherId 教师id
     * @param classesId 班级id
     * @return 返回状态
     */
    BaseResult deleteClass(Integer teacherId, Integer classesId);

    /**
     * 查询一条班级数据
     *
     * @param classes 班级
     * @return 返回状态和数据
     */
    SingleDataResult<Classes> findOneClass(Classes classes);

    /**
     * 查询当前老师所创建的班级
     *
     * @param teacherId 教师id
     * @return 返回状态和班级数据
     */
    MultipleDataResult<Classes> findAllClass(Integer teacherId);

}
