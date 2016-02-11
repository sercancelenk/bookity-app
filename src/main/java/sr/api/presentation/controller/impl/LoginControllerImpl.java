package sr.api.presentation.controller.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import sr.api.presentation.controller.ILoginController;

@Component
public class LoginControllerImpl implements ILoginController {

	@Override
	public ModelAndView getLoginPage() {
		return new ModelAndView("login");
	}

}
