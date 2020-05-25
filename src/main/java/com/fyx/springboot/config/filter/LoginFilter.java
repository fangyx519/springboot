package com.fyx.springboot.config.filter;

/**
 * @Author fyx
 * @Time in 17:34 2020/5/24
 * @Despriction
 */
/*
@Component
@WebFilter(urlPatterns = "*/
/**", filterName = "loginFilter")
public class LoginFilter implements Filter {

    private static final String[] excludePathPatterns = {"/login_1.jsp", "/regist_1.jsp"};


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        HttpSession session = httpServletRequest.getSession();
        Manager manager = (Manager) session.getAttribute("manager");

        String url = httpServletRequest.getRequestURI();

        if (Arrays.asList(excludePathPatterns).contains(url)
                || url.startsWith("/img/")
                || url.startsWith("/css/")
                || url.startsWith("/js/")
                || url.startsWith("/mgr/")
                || !StringUtils.isEmpty(manager)) {

            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/login_1.jsp");
        }

    }

    @Override
    public void destroy() {

    }
}*/
