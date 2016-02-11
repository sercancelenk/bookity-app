package sr.api.presentation.controller.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sr.api.Util.enums.RoleEnums;
import sr.api.business.service.IUserService;
import sr.api.persistence.dao.IUserDao;
import sr.api.persistence.domain.User;
import sr.api.presentation.controller.IViewController;
import sr.api.presentation.vo.Response;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


/**
 * @author sercan
 *
 */
@Component
public class ViewControllerImpl implements IViewController{

	@Autowired
	IUserService userService;

	@Resource(name = "messageSource")
	public MessageSource messageSource;

	@Override
	public String redirectToHome() {
		return "redirect:/protected/home";
	}

	@Override
	public Response registerAction(@RequestBody String data, HttpServletRequest request, Locale locale) {

		JsonObject dataObject = null;
		Response response = new Response();
		if(data == null || "".equals(data)){
			response.setValid(false);
			response.setError(messageSource.getMessage("warn.message.1", null, locale));
			return response;
		}

		Boolean result = false;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try{
			dataObject = gson.fromJson(data, JsonObject.class);
		}catch (Exception ex){
			response.setValid(false);
			response.setError(messageSource.getMessage("warn.message.1", null, locale));
			return response;
		}



		String namesurname = dataObject.get("namesurname") == null ? null : dataObject.get("namesurname").getAsString();
		String email = dataObject.get("email") == null ? null : dataObject.get("email").getAsString();
		String password = dataObject.get("password") == null ? null : dataObject.get("password").getAsString();
		String repassword = dataObject.get("repassword") == null ? null : dataObject.get("repassword").getAsString();

		if(namesurname == null || email == null || "".equals(namesurname) || "".equals(email)
		 || password == null || repassword == null || "".equals(password) || "".equals(repassword)){
			response.setValid(false);
			response.setError(messageSource.getMessage("warn.message.1", null, locale));
			return response;
		}

		if(!password.equals(repassword)){
			response.setValid(false);
			response.setError(messageSource.getMessage("warn.message.2", null, locale));
			return response;
		}

		User existingUser = userService.findByUsername(email);
		if(existingUser != null){
			response.setValid(false);
			response.setError(messageSource.getMessage("warn.message.3", null, locale));
			return response;
		}else{
			result = userService.createUser(namesurname, email, password, RoleEnums.USER, locale, request);
			if(result){
				response.setValid(true);
				response.setError(messageSource.getMessage("success.message.1", null, locale));
				return response;
			}
		}

		response.setValid(false);
		return response;


	}

	@Override
	public ModelAndView registerPage(@RequestBody String data, HttpServletRequest request) {
		return new ModelAndView("register");
	}

	@Override
	public ModelAndView activationPage(@RequestParam(value = "c") String activationCode, HttpServletRequest httpServletRequest, Locale locale) {
		try {
			String message = null;
			User user = userService.checkUserBy(activationCode);
			if (user != null) {
				if (user.getStatus().equals(true)) {
					message = messageSource.getMessage("info.message.1", null, locale);
				} else if (user.getStatus().equals(false)) {
					user.setStatus(true);
					userService.updateUser(user);
					message = messageSource.getMessage("info.message.2", null, locale);
				}
			}
			ModelAndView m = new ModelAndView("activatedpage");
			m.addObject("message", message);

			return m;
		} catch (Exception ex) {

		}

		return null;
	}

}
