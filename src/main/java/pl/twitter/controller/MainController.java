package pl.twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main page controller
 * @author kaz
 *
 */

@Controller
@RequestMapping("")
public class MainController {

	/**
	 * Maps the home page of Twitter
	 * @return
	 */
	@GetMapping("")
	public String mainPage () {
		return "index";
	}
	
	
}
