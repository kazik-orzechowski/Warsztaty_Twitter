package pl.twitter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.twitter.entity.Tweet;
import pl.twitter.entity.User;
import pl.twitter.repository.TweetRepository;
import pl.twitter.repository.UserRepository;
import pl.twitter.entity.User;
/**
 * User sign up and login page controller
 * To be developed along with secure password implementation 
 * and login session based verification 
 * @author kaz
 *
 */
@Controller
@RequestMapping("/user")

public class RegistrationAndLoginController {

	
	@Autowired
	UserRepository repoUser;
	
	@Autowired
	TweetRepository repoTweet;

	/**
	 * Twitter home page login request mapping
	 * 
	 * @return login page 
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * Method used to validate user login and password passed from post form on the login page
	 * !!! To be completed with adding current user to http session !!!
	 * 
	 * @param login - login entered by a guest user on the login page
	 * @param password - password entered by a guest user on the login page
	 * @return login page when login or password are not valid, otherwise returns user home page 
	 */
	
	@PostMapping("/login")
	public String loginVerification (@RequestParam String login, @RequestParam String password ) {
		List<User> users = repoUser.findAll();
		for(User user : users) {
			if(user.getUsername().equals(login) && user.getPassword().equals(password) ) {
				Long id = user.getId();
				return "redirect: /Twitter/tweet/"+id+"/add";
			}
		}
		
		return "login";
	}
	
	/**
	 * User logout request mapping
	 * !!!!! To be completed with http session update
	 * 
	 * @return Twitter home page
	 */
	
	@GetMapping("/logout")
	public String logout() {
		return "index";
	}

	/**
	 * Maps the new user sign up request mapping
	 * 
	 * @param model - Model class object used to pass attributes to target view
	 * @return sign up page
	 */
	@GetMapping("/signup")
	public String addUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}
	
	/**
	 * New {user} sign up form mapping and sign up processing method
	 * !!!!! To be completed with password verification (second password input field) and encryption !!!!!
	 * 
	 * @param user - User class object binded after completion of the post form on the sign up page
	 * @param result - binding result data
	 * @param model - Model class object used to pass attributes to target view
	 * @return sign up page when login, password or email are not valid, otherwise the main application's page
	 */
	@PostMapping("/signup")
	public String addUserPost(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.err.println(result);
			return "signup";
		}
		repoUser.save(user);
		model.addAttribute("allTweets", repoTweet.findByUser(user));
		model.addAttribute("currentUser", user);
		model.addAttribute("tweet", new Tweet());
		return "redirect: /Twitter"; 

	}

}
