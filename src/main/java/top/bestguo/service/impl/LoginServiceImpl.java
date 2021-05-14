package top.bestguo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.bestguo.entity.Student;
import top.bestguo.entity.Teacher;
import top.bestguo.mapper.StudentMapper;
import top.bestguo.mapper.TeacherMapper;
import top.bestguo.service.LoginService;
@Service("LoginService")
@Transactional
public class LoginServiceImpl implements LoginService  {
@Autowired
private StudentMapper studentMapper;
@Autowired
private TeacherMapper teacherMapper;
    @Override
    public Student findStudent(String email, String password) {
        Student student=this.studentMapper.findStudent( email, password);
        return student;
    }

    @Override
    public Teacher findTeacher(String email, String password) {

        Teacher teacher=this.teacherMapper.findTeacher(email,password);
        return teacher;
    }
}
