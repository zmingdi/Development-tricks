package com.mingdi101.annotations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 与thymleaf集成， 通过controller接收请求， 将请求参数和转发地址封装于ModelAndView对象当中。
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/controller")
public class ControllerAnnotation {

	@GetMapping("/hi")
	public ModelAndView getPage(@RequestParam("guestName") String guestName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ControllerAnnotation");
		mv.addObject("greeting", "welcome");
		mv.addObject("guestName", guestName);
		return mv;
	}
	
	/**
	 * @Controller annotation uses as a router to hide the url path of static pages.
	 * it returns a string object and spring container with check the corresponding page 
	 * according to 'spring.thymeleaf.prefix' configuration in application.properties
	 * @return
	 */
	@GetMapping("/index") 
	public String indexPage() {
		return "ControllerAnnotationIndex";
	}
	@GetMapping("/responseObject")
	public ModelAndView getObject(@RequestParam("guestName") String guestName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ControllerAnnotation");
		mv.addObject("greeting", "welcome");
		mv.addObject("guestName", guestName);
		return mv;
	}
}
