package sr.api.presentation.controller;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import sr.api.persistence.domain.Book;
import sr.api.presentation.aspect.PrepareResponse;
import sr.api.presentation.vo.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sercan
 *
 */
@Controller
@RequestMapping(value = "/sc/books")
public interface IBookController {
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView books();
	
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listAll(@RequestParam int page, Locale locale);

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> create(
			@ModelAttribute("book") Book book);

	@RequestMapping(value = "/u/{id}", method = RequestMethod.POST, produces = "application/json", consumes="application/json")
	public ResponseEntity<?> update(
			@PathVariable("id") String bookId,
			@RequestBody Book book);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> delete(
			@PathVariable("id") String bookId);

	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	Response searchAnything(@RequestBody String searchData, HttpServletRequest request, Locale locale);
	
}
