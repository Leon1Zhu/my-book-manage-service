package com.alien.Interceptor;


import com.alien.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by zhuliang on 2017/4/5.
 */

public class WebInterceptor extends HandlerInterceptorAdapter {
   /* @Value("${jwt.audience}")
    private String audience;
    @Value("${jwt.base64Security}")
    private String base64Security;
*/
    /**
     *预处理回调方法，实现处理器的预处理（如登录检查）。
     *第三个参数为响应的处理器，即controller。
     *返回true，表示继续流程，调用下一个拦截器或者处理器。
     *返回false，表示流程中断，通过response产生响应。
     */
  /*  @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("-------------------preHandle");
        String data = "token error";
        try {

        String jwtToken = request.getHeader("Authorization");
        // 验证用户是否登陆
        Claims claims = JwtUtils.parseJWT(jwtToken, "base64");
        if("测试系统".equals(claims.getAudience())){
            if (new Date().getTime() > claims.getExpiration().getTime()) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response = setResponse(data,response);
                return false;
            }else{
                return true;
            }
        }
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response = setResponse(data,response);
         return false;
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response = setResponse(data,response);
            return false;
        }
    }

    public HttpServletResponse setResponse(String data, HttpServletResponse response)throws Exception{
        OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
        response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        byte[] dataByteArr = data.getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
        outputStream.write(dataByteArr);//使用OutputStream流向客户端输出字节数组
        return response;
    }
*/
    /**
     *当前请求进行处理之后，也就是Controller 方法调用之后执行，
     *但是它会在DispatcherServlet 进行视图返回渲染之前被调用。
     *此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("-------------------postHandle");
        System.out.println(handler);
    }

    /**
     *方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。
     *这个方法的主要作用是用于进行资源清理工作的。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("-------------------afterCompletion");
    }

}