package top.bestguo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.bestguo.entity.Student;
import top.bestguo.entity.Teacher;
import top.bestguo.mapper.StudentMapper;
import top.bestguo.mapper.TeacherMapper;
import top.bestguo.service.RegisterService;

@Service("RegisterService")
@Transactional

public class RegisterServiceImpl implements RegisterService {
@Autowired
public StudentMapper studentMapper;
@Autowired
public TeacherMapper teacherMapper;

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }
}
