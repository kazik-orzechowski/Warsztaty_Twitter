package pl.twitter.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import pl.twitter.entity.Comment;
import pl.twitter.entity.Contact;
import pl.twitter.entity.Tweet;
import pl.twitter.entity.User;
import pl.twitter.repository.CommentRepository;
import pl.twitter.repository.ContactRepository;
import pl.twitter.repository.TweetRepository;
import pl.twitter.repository.UserRepository;

@Controller
@RequestMapping("/tweet")
public class TweetController {

	@Autowired
	private TweetRepository repoTweet;
	@Autowired
	private UserRepository repoUser;
	@Autowired
	private ContactRepository repoContact;
	@Autowired
	private CommentRepository repoComment;

		@Transactional
	@GetMapping("/{id}/add")
	public String addTweet(@PathVariable Long id, Model model) {
		Tweet tweet = new Tweet();
					
		System.err.println(tweet.toString());
		
		User user = repoUser.findOne(id);
		Hibernate.initialize(user.getTweets());
		model.addAttribute("allTweets", repoTweet.findByUser(user));
		model.addAttribute("currentUser", user);
		model.addAttribute("tweet", tweet);
		return "tweets";
	}

	@Transactional
	@PostMapping("/{id}/add")
	public String addTweet2(@PathVariable Long id, @Valid Tweet tweet, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "tweets";
		}
		Hibernate.initialize(repoUser.getOne(id).getTweets());
		System.err.println("post" + tweet.toString());
		tweet.setUser(repoUser.getOne(id));
		// DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		tweet.setCreated(date);
		if (repoTweet.findTop1ByOrderByIdDesc() == null) {
			tweet.setId(1L);
		} else { 
			tweet.setId(1L + repoTweet.findTop1ByOrderByIdDesc().getId());
		}
		repoTweet.save(tweet);
		return "redirect: /Twitter/tweet/" + id + "/add";
	}

	@GetMapping("/{id}/delete/{tweetId}")
	public String delTweet(@PathVariable Long id, @PathVariable Long tweetId, Model model) {
		repoTweet.delete(tweetId);
		return "redirect: /Twitter/tweet/" + id + "/add";
	}

	@GetMapping("/{id}/details/{tweetId}")
	public String detailedTweet(@PathVariable Long id, @PathVariable Long tweetId, Model model) {
		model.addAttribute("tweet", repoTweet.findOne(tweetId));
		model.addAttribute("currentUser", repoUser.findOne(id));
		return "tweetDetails";
	}
	@Transactional
	@GetMapping("/{id}/guestTweets/{idc}")
	public String guestTweets(@PathVariable Long id, @PathVariable Long idc, Model model) {
		User guestUser = repoUser.findOne(idc);
		User hostUser = repoUser.findOne(id);
		Hibernate.initialize(guestUser.getTweets());
		model.addAttribute("guestTweets", repoTweet.findByUser(guestUser));
		model.addAttribute("currentUser", hostUser);
		model.addAttribute("guestUser", guestUser);
		return "guestTweets";
	}

	@Transactional
	@GetMapping("/{id}/guestTweets")
	public String allGuestsTweets(@PathVariable Long id, Model model) {
		
		List<Tweet> followedTweets = defineFollowedTweets(id);
		
		User hostUser = initializeTweets(id, followedTweets);

		model.addAttribute("guestTweets", followedTweets);
		model.addAttribute("currentUser", hostUser);
		model.addAttribute("inputDisplayNumber", 0);
		model.addAttribute("displayNumber", 0);		
		return "allGuestsTweets";
	}

		
	@Transactional
	@GetMapping("/{id}/comment/{idt}/post")
	public String addComment(@PathVariable Long id,@PathVariable Long idt, Model model) {
		Comment comment = new Comment();
		
		List<Tweet> followedTweets = defineFollowedTweets(id);
		User hostUser = initializeTweets(id, followedTweets);
		
		model.addAttribute("guestTweets", followedTweets);
		model.addAttribute("currentUser", hostUser);
		model.addAttribute("inputDisplayNumber", idt);
		model.addAttribute("comment", comment);
		return "allGuestsTweets";
	}

	@Transactional
	@PostMapping("/{id}/comment/{idt}/post")
	public String addCommentPost(@PathVariable Long id, @PathVariable Long idt, 
					@Valid Comment comment, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "allGuestsTweets";
		}
		Hibernate.initialize(repoUser.getOne(id).getTweets());
		Date date = new Date();
		comment.setCreated(date);
		comment.setTweet(repoTweet.getOne(idt));
		comment.setUser(repoUser.getOne(id));
		if(repoComment.findTop1ByOrderByIdDesc() == null) {
			comment.setId(1L);
		} else { 		
		comment.setId(1L + repoComment.findTop1ByOrderByIdDesc().getId());
		}
		repoComment.save(comment);
		List<Tweet> followedTweets = defineFollowedTweets(id);
		User hostUser = initializeTweets(id, followedTweets);
		
		model.addAttribute("guestTweets", followedTweets);
		model.addAttribute("currentUser", hostUser);
		model.addAttribute("inputDisplayNumber", 0);
		model.addAttribute("displayNumber", idt);
		model.addAttribute("tweetComments", repoComment.findAllByTweetId(idt));
		model.addAttribute("comment", comment);
		return "allGuestsTweets";

	}	
	
	@Transactional
	@GetMapping("/{id}/comment/{idt}/show")
	public String showComments(@PathVariable Long id,@PathVariable Long idt, Model model) {
		
		List<Tweet> followedTweets = defineFollowedTweets(id);
		User hostUser = initializeTweets(id, followedTweets);
		
		model.addAttribute("guestTweets", followedTweets);
		model.addAttribute("currentUser", hostUser);
		model.addAttribute("inputDisplayNumber", 0);
		model.addAttribute("displayNumber", idt);
		model.addAttribute("tweetComments", repoComment.findAllByTweetId(idt));
		return "allGuestsTweets";
	}
	
	/**
	 * Initialization of comments for each tweet
	 * @param id
	 * @param followedTweets
	 * @return host user with tweets initialized 
	 */
	
	private User initializeTweets(Long id, List<Tweet> followedTweets) {
		User hostUser = repoUser.findOne(id);
		for(Tweet tweet : followedTweets) {
			Hibernate.initialize(tweet.getComment());
		}
		return hostUser;
	}

	/**
	 * Method defining list of tweets to be displayed
	 * @param id
	 * @return list of all tweets of all followed (guest) users followed by host user
	 */
	private List<Tweet> defineFollowedTweets(Long id) {
		List<Contact> myFollowedContacts = repoContact.findAllByHostIdAndStatus(id, 2);
		List<User> followedUsers = new ArrayList<User>();
		for(Contact contact : myFollowedContacts) {
			followedUsers.add(contact.getGuest());
		}
			
		List<Tweet> followedTweets = 
				repoTweet.findTweetsOfUsersFollowedByMeOrderDesc(followedUsers);
		return followedTweets;
	}
	

	
}
