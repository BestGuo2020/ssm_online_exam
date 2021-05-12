package top.bestguo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static top.bestguo.interceptor.TeacherLoginInterceptor.isLoginOutOfDate;

/**
 * 学生端登录拦截器
 */
public class StudentLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取 session
        Object o = request.getSession().getAttribute("student");
        return isLoginOutOfDate(request, response, o);
    }

}
