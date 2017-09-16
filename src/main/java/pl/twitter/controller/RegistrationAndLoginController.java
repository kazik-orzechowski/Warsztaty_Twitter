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

@Controller
@RequestMapping("/users")

public class RegistrationAndLoginController {

	@Autowired
	UserRepository repoUser;

	@Autowired
	TweetRepository repoTweet;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

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
	
	
	@GetMapping("/logout")
	public String logout() {
		return "index";
	}

	
	// @GetMapping("/signup")
	// public String signUp () {
	// return "signup";
	// }

	@GetMapping("/signup")
	public String addUser(Model model) {
		User user = new User();
		user.setEnabled(false);
		model.addAttribute("user", user);
		return "signup";
	}

	@PostMapping("/signup")
	public String addUserPost(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "signup";
		}
		repoUser.save(user);
		model.addAttribute("allTweets", repoTweet.findByUser(user));
		model.addAttribute("currentUser", user);
		model.addAttribute("tweet", new Tweet());
		
		// poprawic mapowanie projektu by byl sam Tweet
		return "redirect: /Twitter/tweet/"+user.getId()+"/add"; 

	}

}
