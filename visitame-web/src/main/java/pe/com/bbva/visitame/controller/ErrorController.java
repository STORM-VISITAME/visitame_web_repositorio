package pe.com.bbva.visitame.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	
	
	@RequestMapping(value = "errors")
	public ModelAndView errors(HttpServletRequest httpRequest){
		
		ModelAndView modelAndView = null;
		
		int httpErrorCode = getErrorCode(httpRequest);
		
		if(httpErrorCode == 404) {
			modelAndView = new ModelAndView("index");
		}else {
			modelAndView = new ModelAndView("error");
		}
		
		return modelAndView;
	}
	
	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
	
}
