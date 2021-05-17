package top.bestguo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
import top.bestguo.render.MultipleDataResult;
import top.bestguo.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Service("StudentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper  studentMapper;
    @Autowired
    StudentClassMapper studentClassMapper;
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

    @Override
    public int studentJoinClass(StudentClass studentClass) {
        return studentClassMapper.joinClass(studentClass);
    }
    /**
     * 通过班级id查询当前班级的学生信息
     *
     * @param classId 班级号
     * @param p 当前页
     * @param limit 当前页展示的数据条数
     * @return 学生信息
     */
    @Override
    public MultipleDataResult<Student> selectStudentByClassId(Integer classId, Integer p, Integer limit) {
        MultipleDataResult<Student> result = new MultipleDataResult<>();
        /*// 设定查询条件，按班级号查询
        QueryWrapper<StudentClass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("classid", classId);
        // 查询数据
        List<StudentClass> studentClassList = studentClassMapper.selectList(queryWrapper);
        // 创建一个列表，用于保存学生数据
        List<Student> students = new ArrayList<>();
        // 遍历出数据，在循环中查询学生信息，并且添加到数据中
        for (StudentClass studentClass : studentClassList) {
            // 查询学生信息
            Student student = studentMapper.selectById(studentClass.getStuid());
            students.add(student);
        }*/
        // 设定分页
        PageHelper.startPage(p, limit);
        // 开始查询
        List<Student> students = studentMapper.selectStudentByClassId(classId);
        // 获取返回的信息
        PageInfo<Student> s = new PageInfo<>(students);
        // 设置总计数
        result.setTotal((int) s.getTotal());
        // 设置返回结果
        result.setData(s.getList());
        // 设置状态码和消息
        if(s.getList().size() > 0) {
            result.setCode(0);
            result.setMessage("查询成功");
        } else {
            result.setCode(1);
            result.setMessage("无结果");
        }

        return result;
    }
}
