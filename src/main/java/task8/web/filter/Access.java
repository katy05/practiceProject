package task8.web.filter;




import task8.dao.InMemoryUserDao;
import task8.dao.UserDao;
import task8.service.AdminService;
import task8.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Access")
public class Access implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
    private AdminService adminService = ServiceFactory.getInstance().getService();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        UserDao userDao = new InMemoryUserDao();
        String loginURI = req.getContextPath() + "/auth.jhtml";
        String welcomeURI = req.getContextPath() + "/welcome.jhtml";
        String logoutURI = req.getContextPath() + "/logout.jhtml";

        boolean isLoginPage = req.getRequestURI().equals(loginURI);
        boolean isLogoutPage = req.getRequestURI().equals(logoutURI);
        boolean isWelcomePage = req.getRequestURI().equals(welcomeURI);

        if(isLoginPage || isLogoutPage || isWelcomePage){
            filterChain.doFilter(req, resp);
        }
        else {

                boolean isRoot = true;//(boolean) session.getAttribute("isRoot");

                if (!isLoginPage && !isLogoutPage && !isWelcomePage && !isRoot) {
                    resp.sendRedirect(welcomeURI);
                } else {
                    filterChain.doFilter(req, resp);
                }
            }
        }

}

