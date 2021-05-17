package top.bestguo.service;

import top.bestguo.entity.Student;
import top.bestguo.entity.StudentClass;
import top.bestguo.entity.Teacher;
import top.bestguo.render.BaseResult;

import top.bestguo.render.SingleDataResult;
import top.bestguo.vo.ClassInfo;

import top.bestguo.render.MultipleDataResult;

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
    /**
     * 查询班级编号
     */
    ClassInfo selectClassCodeByName(Integer classCode);
    /**
     * 加入班级
     */
    int studentJoinClass(StudentClass studentClass);

    /**
     * 通过班级id查询当前班级的学生信息
     *
     * @param classId 班级号
     * @param p 当前页
     * @param limit 当前页展示的数据条数
     * @return 学生信息
     */
    MultipleDataResult<Student> selectStudentByClassId(Integer classId, Integer p, Integer limit);
}
