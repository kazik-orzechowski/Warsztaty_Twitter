package pl.twitter.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.twitter.entity.Tweet;
import pl.twitter.entity.User;
import pl.twitter.repository.TweetRepository;
import pl.twitter.repository.UserRepository;

@Controller
@RequestMapping("/comment")
public class CommentController {

//	@Autowired
//	private TweetRepository repoTweet;
//	@Autowired
//	private UserRepository repoUser;
//	
//	
////	@GetMapping("/{id}")
////	public String tweets(@PathVariable Long id, Model model) {
////		model.addAttribute("allTweets", repoTweet.findByUserId(id));
////		return "tweets";
////	}
//	@Transactional
//	@GetMapping("/{id}/add")
//	public String addTweet(@PathVariable Long id, Model model) {
//		Tweet tweet = new Tweet();
//		User user = repoUser.findOne(id);
////		user.setId(id);
////		tweet.setUser(user);
//		
//		Hibernate.initialize(user.getTweets());
//		model.addAttribute("allTweets", repoTweet.findByUser(user));
//		System.out.println(repoTweet.findAll());
//		model.addAttribute("currentUser", user);
//		model.addAttribute("tweet", tweet);
//		return "tweets";
//	}
//	
//	@Transactional
//	@PostMapping("/{id}/add")
//	public String addTweet2(@PathVariable Long id, @Valid Tweet tweet, BindingResult result, Model model) {
//		if(result.hasErrors()) {
//			return "tweets";
//		}
//		Hibernate.initialize(repoUser.getOne(id).getTweets());
//		tweet.setUser(repoUser.getOne(id));
////		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date date= new Date();
//		tweet.setCreated(date);
//		repoTweet.save(tweet);
//		return "redirect: /Twitter/tweet/"+id+"/add"; 
////		
////		model.addAttribute("allTweets", repoTweet.findByUser(tweet.getUser()));
////		model.addAttribute("currentUser", tweet.getUser());
////		Tweet emptyTweet = new Tweet();
////		model.addAttribute("tweet", emptyTweet);
////		return "tweets";
////		
//	}
//	
//	@GetMapping("/{id}/delete/{tweetId}")
//	public String delTweet(@PathVariable Long id, @PathVariable Long tweetId, Model model) {
//		repoTweet.delete(tweetId);
//		return "redirect: /Twitter/tweet/"+id+"/add"; 
//	}
//	
//	@GetMapping("/{id}/details/{tweetId}")
//	public String detailedTweet(@PathVariable Long id, @PathVariable Long tweetId, Model model) {
//		model.addAttribute("tweet", repoTweet.findOne(tweetId));
//		model.addAttribute("currentUser", repoUser.findOne(id));
//		return "tweetDetails"; 
//	}
//	
}
