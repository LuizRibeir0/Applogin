package br.appLogin.appLogin.service.autenticator;

import br.appLogin.appLogin.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final CookieService cookieService;

    @Autowired
    public LoginInterceptor(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @Override

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (cookieService.getCookie(request, "usuarioId") != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
