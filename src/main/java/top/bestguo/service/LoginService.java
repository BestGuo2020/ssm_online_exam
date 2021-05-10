package top.bestguo.service;

import top.bestguo.entity.Student;
import top.bestguo.entity.Teacher;

public interface LoginService  {
  Student findStudent(String email,String password);
  Teacher findTeacher(String email, String password);
}
