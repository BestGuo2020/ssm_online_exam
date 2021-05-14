package top.bestguo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bestguo.entity.Teacher;
import top.bestguo.mapper.TeacherMapper;
import top.bestguo.render.BaseResult;
import top.bestguo.service.TeacherService;

/**
 * 教师相关的业务逻辑实现类
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 修改教师信息
     *
     * @param teacher 教师实体类
     * @return 返回状态
     */
    @Override
    public BaseResult modifyTeacherInfo(Teacher teacher) {
        BaseResult result = new BaseResult();
        int upd = teacherMapper.updateById(teacher);
        if(upd > 0) {
            result.setCode(0);
            result.setMessage("教师信息修改成功");
        } else {
            result.setCode(1);
            result.setMessage("教师信息修改失败");
        }
        return result;
    }

    /**
     * 通过 id 寻找教师信息
     *
     * @param id 教师id
     * @return 教师实体类
     */
    @Override
    public Teacher selectTeacherById(Integer id) {
        return teacherMapper.selectById(id);
    }

    /**
     * 修改密码
     *
     * @param id 教师id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 返回修改状态
     */
    @Override
    public BaseResult modifyTeacherPassword(Integer id, String oldPassword, String newPassword) {
        BaseResult result = new BaseResult();
        // 查询教师信息，得到密码
        Teacher teacher = teacherMapper.selectById(id);
        String password = teacher.getPassword();
        // 判断输入的密码是否一致
        if(!password.equals(oldPassword)){
            result.setCode(1);
            result.setMessage("输入的原密码有误");
        } else {
            // 修改密码
            teacher.setPassword(newPassword);
            int upd = teacherMapper.updateById(teacher);
            if(upd > 0) {
                result.setCode(0);
                result.setMessage("密码修改成功");
            } else {
                result.setCode(1);
                result.setMessage("密码修改失败");
            }
        }

        return result;
    }
}
