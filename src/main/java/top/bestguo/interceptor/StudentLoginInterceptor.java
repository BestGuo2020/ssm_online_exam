package top.bestguo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static top.bestguo.interceptor.TeacherLoginInterceptor.isLoginOutOfDate;

/**
 * 学生端登录拦截器
 */
public class StudentLoginInterceptor implements HandlerInterceptor {

    /**
     * 判断登录是否过期
     *
     * @param request 从浏览器端得到的请求信息
     * @param response 从服务器端响应的数据
     * @param handler 传递的 Session 对象
     * @return 是否登陆
     * @throws Exception 返回的异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取 session
        Object o = request.getSession().getAttribute("student");
        System.out.println(o);
        return isLoginOutOfDate(request, response, o);
    }

}
