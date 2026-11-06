package com.ecommerce.config;

import com.ecommerce.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AutoLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        HttpSession session = request.getSession(true);


        if (session.getAttribute("currentUser") == null){
            Object autoLoginUser = request.getServletContext().getAttribute("autoLoginUser");

            System.out.println("DEBUG - INTERCEPTOR - Path: " + request.getRequestURI());
            System.out.println("DEBUG - INTERCEPTOR - autoLoginUser from context: " + autoLoginUser);

            if (autoLoginUser != null) {
                session.setAttribute("currentUser", autoLoginUser);
                System.out.println("Auto-login: User " + ((User) autoLoginUser).getEmail() + " autocatically logged in for session.");
            }else{
                System.out.println("DEBUG - INTERCEPTOR - autoLoginUser is null, checking ServeletContext atributes");
            }
        }else{
            System.out.println("DEBUG - INTERCEPTOR - currentUser already in session: " + ((User)session.getAttribute("currentUser")).getEmail());
        }


        return true;
    }
}
