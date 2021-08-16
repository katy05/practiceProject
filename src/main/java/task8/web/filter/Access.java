package task8.web.filter;

import org.springframework.beans.factory.annotation.Autowired;
import task8.domain.Role;
import task8.service.AdminService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(filterName = "Access", urlPatterns = "*.jhtml")
public class Access implements Filter {

    @Autowired
    AdminService adminService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        // UserDao userDao = new InMemoryUserDao();
        String loginURI = req.getContextPath() + "/auth.jhtml";
        String welcomeURI = req.getContextPath() + "/welcome.jhtml";
        String logoutURI = req.getContextPath() + "/logout.jhtml";

        boolean isLoginPage = req.getRequestURI().equals(loginURI);
        boolean isLogoutPage = req.getRequestURI().equals(logoutURI);
        boolean isWelcomePage = req.getRequestURI().equals(welcomeURI);

        if (isLoginPage || isLogoutPage || isWelcomePage) {
            filterChain.doFilter(req, resp);
        } else {

            ArrayList<Role> userRoles = (ArrayList) session.getAttribute("roles");
            final boolean[] isRoot = {false};
            if (userRoles != null)
                userRoles.forEach(role -> {
                    if (role.getName().equals("root")) {
                        isRoot[0] = true;
                    }
                });
            if (!isLoginPage && !isLogoutPage && !isWelcomePage && !isRoot[0]) {
                resp.sendRedirect(welcomeURI);
            } else {
                filterChain.doFilter(req, resp);
            }
        }
    }

}

