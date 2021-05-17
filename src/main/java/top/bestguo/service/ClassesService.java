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
     * 删除多个班级
     *
     * @param teacherId 教师id
     * @param classesId 班级id
     * @return 返回状态
     */
    BaseResult deleteClass(Integer teacherId, Integer[] classesId);

    /**
     * 查询一条班级数据
     *
     * @param classes 班级
     * @return 返回状态和数据
     */
    SingleDataResult<Classes> findOneClass(Classes classes);


    /**
     * 通过班级吗查询一条班级信息
     * @param classcode 班级码
     * @return 返回班级信息
     */
    Classes findOneClassByClassCode(Integer classcode);

    /**
     * 通过班级id和学生id查询一条班级信息
     * @param classId
     * @param stuId
     * @return
     */
    Boolean isStudentAtTheClass(Integer classId, Integer stuId);

    /**
     * 查询当前老师所创建的班级
     *
     * @param teacherId 教师id
     * @return 返回状态和班级数据
     */
    MultipleDataResult<Classes> findAllClass(Integer teacherId);
    /**
     * 查询当前老师所管理的班级信息
     *
     * @param teacherId 教师id
     * @param p 当前页
     * @param limit 当前页展示的数据条数
     * @return 当前页的班级信息
     */
    MultipleDataResult<Classes> findAllClass(Integer teacherId, Integer p, Integer limit);

    /**
     * 从班级踢出学生
     *
     * @param classId 班级id
     * @param stuId 学生id
     * @return 返回状态
     */
    BaseResult kickOutStudent(Integer classId, Integer stuId);

    /**
     * 从班级踢出多个学生
     *
     * @param classId 班级id
     * @param stuIds 学生id
     * @return 返回状态
     */
    BaseResult kickOutStudent(Integer classId, Integer[] stuIds);

    /**
     * 学生退出班级
     *
     * @param stuId 学生id
     * @param classId 班级id
     * @return 返回退出状态
     */
    BaseResult deleteJoinClass(Integer stuId, Integer classId);
    /**
     * 学生退出多个班级
     *
     * @param stuId 学生id
     * @param classIds 班级id
     * @return 返回退出状态
     */
    BaseResult deleteJoinClass(Integer stuId, Integer[] classIds);
    /**
     * 查询学生所加入的班级
     *
     * @param studentId 学生id
     * @return 返回状态和班级数据
     */


    MultipleDataResult<?> findJoinClass(Integer studentId);
}
