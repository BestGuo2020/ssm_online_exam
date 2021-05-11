package top.bestguo.service;

import top.bestguo.entity.Teacher;
import top.bestguo.render.BaseResult;

/**
 * 教师相关的业务逻辑的操作
 */
public interface TeacherService {

    /**
     * 修改教师信息
     *
     * @param teacher 教师实体类
     * @return 返回修改状态
     */
    BaseResult modifyTeacherInfo(Teacher teacher);

    /**
     * 通过 id 寻找教师信息
     *
     * @param id 教师id
     * @return 教师实体类
     */
    Teacher selectTeacherById(Integer id);

    /**
     * 修改密码
     *
     * @param id 教师id
     * @param oldPassword 旧密码
     * @return 返回修改状态
     */
    BaseResult modifyTeacherPassword(Integer id, String oldPassword, String newPassword);

}
