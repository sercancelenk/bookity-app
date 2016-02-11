package sr.api.presentation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import sr.api.business.service.impl.AppUserDetailsService;
import sr.api.persistence.domain.User;


/**
 * @author sercan
 *
 */
public class LoginPage extends HandlerInterceptorAdapter {
	@Autowired AppUserDetailsService appUserDetailsService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
        if(user == null){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            user = appUserDetailsService.getUserDetail(email);
            session.setAttribute("user", user);
        }

        return super.preHandle(request, response, handler);
	}
	
	
}
