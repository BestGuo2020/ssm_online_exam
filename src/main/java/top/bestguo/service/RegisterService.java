package top.bestguo.service;

import top.bestguo.entity.Student;
import top.bestguo.entity.Teacher;

public interface RegisterService {
    int addStudent(Student student);
    int addTeacher(Teacher teacher);
}
