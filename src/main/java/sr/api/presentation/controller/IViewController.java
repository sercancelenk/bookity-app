package sr.api.presentation.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sr.api.presentation.vo.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author sercan
 *
 */
@Component
@RequestMapping("/")
public interface IViewController {
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public String redirectToHome();

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody Response registerAction(@RequestBody String data, HttpServletRequest request, Locale locale);

    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    ModelAndView registerPage(@RequestBody String data, HttpServletRequest request);

    @RequestMapping(value = "/register/activation.do", method = {RequestMethod.GET})
    ModelAndView activationPage(@RequestParam(value = "c") String activationCode, HttpServletRequest httpServletRequest, Locale locale);
}
