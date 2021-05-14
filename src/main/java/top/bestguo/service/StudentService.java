package top.bestguo.service;

import top.bestguo.entity.Student;
import top.bestguo.entity.Teacher;
import top.bestguo.render.BaseResult;

/**
 * 学生服务类
 *
 * 教师：查询学生、踢出该班级
 * 学生：账号注册、信息修改
 */
public interface StudentService {
    /**
     * 通过 id 寻找学生信息
     *
     * @param id 学生id
     * @return 学生实体类
     */
    Student selectStudentById(Integer id);
    /**
     * 修改学生密码
     *
     * @param id 学生id
     * @param oldPassword 旧密码
     * @return 返回修改状态
     */
    BaseResult modifyStudentPassword(Integer id, String oldPassword, String newPassword);
    /**
     * 修改学生信息
     *
     * @param student 学生 实体类
     * @return 返回修改状态
     */
    BaseResult modifyStudentInfo(Student student);
}
