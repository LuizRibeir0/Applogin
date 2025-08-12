package br.appLogin.appLogin.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CookieService {

    public void setCookie(HttpServletResponse response, String key,  String valor, int segundos) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie(key, URLEncoder.encode(valor,"UTF-8"));
        cookie.setMaxAge(segundos);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    public  String getCookie(HttpServletRequest request, String key) throws UnsupportedEncodingException {
        String Valor = Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(request.getCookies())
                .filter(cookie -> key.equals(cookie.getName ()))
                .findAny()
                ).map(e -> e.getValue())
                .orElse(null);

        if (Valor != null) {
            Valor =  URLDecoder.decode(Valor,"UTF-8");
            return Valor;
        }

        return Valor;
    }
}
