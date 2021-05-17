package top.bestguo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bestguo.entity.Student;
import top.bestguo.entity.StudentClass;
import top.bestguo.entity.Teacher;
import top.bestguo.mapper.StudentClassMapper;
import top.bestguo.mapper.StudentMapper;
import top.bestguo.render.BaseResult;
import top.bestguo.render.SingleDataResult;
import top.bestguo.service.StudentService;
import top.bestguo.vo.ClassInfo;

@Service("StudentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper  studentMapper;
    @Override
    public Student selectStudentById(Integer id) {

        return studentMapper.selectById(id);
    }

    @Override
    public BaseResult modifyStudentPassword(Integer id, String oldPassword, String newPassword) {
        BaseResult result = new BaseResult();
        // 查询学生 信息，得到密码
        Student student = studentMapper.selectById(id);
        String password = student.getPassword();
        // 判断输入的密码是否一致
        if (!password.equals(oldPassword)) {
            result.setCode(1);
            result.setMessage("输入的原密码有误");
        } else {
            // 修改密码
            student.setPassword(newPassword);
            int upd = studentMapper.updateById(student);
            if (upd > 0) {
                result.setCode(0);
                result.setMessage("密码修改成功");
            } else {
                result.setCode(1);
                result.setMessage("密码修改失败");
            }
        }
        return result;
    }
    @Override
    public BaseResult modifyStudentInfo(Student student) {
        BaseResult result = new BaseResult();
        int upd = studentMapper.updateById(student);
        if(upd > 0) {
            result.setCode(0);
            result.setMessage("学生信息修改成功");
        } else {
            result.setCode(1);
            result.setMessage("学生信息修改失败");
        }
        return result;
    }

    @Override
    public ClassInfo selectClassCodeByName(Integer classCode) {
        return studentMapper.findClassInfo(classCode);
    }

    @Autowired
    StudentClassMapper studentClassMapper;
    @Override
    public int studentJoinClass(StudentClass studentClass) {
        return studentClassMapper.joinClass(studentClass);
    }
}
