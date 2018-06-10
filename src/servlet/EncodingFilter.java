package servlet;

/**
 * Created by 王少刚 on 2018/6/3.
 * 这是过滤器，处理全局中文乱码问题。
 */

import java.io.IOException;
import javax.servlet.*;

public class EncodingFilter implements Filter{
    public void init(FilterConfig config) throws ServletException {}
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        request.setCharacterEncoding("gb2312");
        response.setCharacterEncoding("gb2312");
        chain.doFilter(request, response);
    }
    public void destroy() {}
}
