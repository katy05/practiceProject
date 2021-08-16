package task8.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Abth", urlPatterns = "*.jhtml")
public class Abth implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);

        String loginURI = req.getContextPath() + "/auth.jhtml";
        boolean isLoginPage = req.getRequestURI().equals(loginURI);
        boolean userIsLogged = session != null && session.getAttribute("login") != null;


        if (isLoginPage || userIsLogged) {
//            if (req.getRequestURI().matches(".*[css|jpg|png|gif|js].*")) {
//                filterChain.doFilter(req, resp);
//                return;
//            }
            filterChain.doFilter(req, resp);

        } else {
            resp.sendRedirect(loginURI);
        }


    }

}
