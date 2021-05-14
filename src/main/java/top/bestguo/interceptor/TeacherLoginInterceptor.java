package top.bestguo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 教师端登录拦截器
 */
public class TeacherLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取 session
        Object o = request.getSession().getAttribute("teacher");
        return isLoginOutOfDate(request, response, o);
    }

    /**
     * 判断登录是否过期
     *
     * @param request 从浏览器端得到的请求信息
     * @param response 从服务器端响应的数据
     * @param o 传递的 Session 对象
     * @return 是否登陆
     * @throws Exception 返回的异常
     */
    static boolean isLoginOutOfDate(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 判断是否为异步请求
        String async = request.getHeader("X-Requested-With");
        if(!"XMLHttpRequest".equals(async)){
            if(o == null) {
                // 重定向到过期页面
                request.getRequestDispatcher("/WEB-INF/view/commons/session_out.jsp").forward(request, response);
                return false;
            }
        } else {
            if(o == null) {
                // 异步请求返回的 json 数据
                String res = "{\"code\":-1, \"message\":\"页面已过期，请重新登录！\"}";
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write(res);
                return false;
            }
        }
        return true;
    }
}
