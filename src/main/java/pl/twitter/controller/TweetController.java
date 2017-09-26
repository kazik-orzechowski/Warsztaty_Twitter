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

/**
 * Main Twitter controller responsible to process user's requests concerning tweets and comments
 * @author kaz
 *
 */

@Controller
@RequestMapping("/tweet")
public class TweetController {

	/** 
	 * Jpa repository reference to Tweet class
	 */
	@Autowired
	private TweetRepository repoTweet;
	/** 
	 * Jpa repository reference to User class
	 */

	@Autowired
	private UserRepository repoUser;
	/** 
	 * Jpa repository reference to Contact class
	 */

	@Autowired
	private ContactRepository repoContact;
	/** 
	 * Jpa repository reference to Comment class
	 */

	@Autowired
	private CommentRepository repoComment;

	/**
	 * Method mapping user of given {id} request and preparing display of current user home page containing 
	 * tweet input form and list of user's own tweets
	 * 
	 * @param id
	 * @param model
	 * @return current user home page
	 */
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

	/**
	 * Method mapping user of given {id} input request regarding a new {tweet} including input data (tweet title and tweet text) 
	 * validation, setting an id, a date and user for the new tweet 
	 * 
	 * !!!!! To be completed with saving record to database verification !!!!!
	 * @param id
	 * @param tweet
	 * @param result
	 * @param model
	 * @return tweet input form on user home page (to be amended) or user home page with new tweet 
	 * added to the displayed list
	 */
	
	@Transactional
	@PostMapping("/{id}/add")
	public String addTweet2(@PathVariable Long id, @Valid Tweet tweet, 
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.err.println(result);
			return "tweets";
		}
		Hibernate.initialize(repoUser.getOne(id).getTweets());
		System.err.println("post" + tweet.toString());
		tweet.setUser(repoUser.getOne(id));
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

	/** 
	 * Method mapping user request with given {id} concerning removal of tweet with given {idt} 
	 * and deleting this tweet
	 * !!!!! To be completed with saving record to database verification !!!!!
	 * 
	 * @param id
	 * @param tweetId
	 * @param model
	 * @return user home page
	 */
	
	@GetMapping("/{id}/delete/{idt}")
	public String delTweet(@PathVariable Long id, @PathVariable Long idt, Model model) {
		repoTweet.delete(idt);
		return "redirect: /Twitter/tweet/" + id + "/add";
	}

	/**
	 * Method mapping user with given {id} request to display details of tweet with given {idt}
	 * @param id
	 * @param tweetId
	 * @param model
	 * @return tweet datails page
	 */
	@GetMapping("/{id}/details/{idt}")
	public String detailedTweet(@PathVariable Long id, @PathVariable Long idt, Model model) {
		model.addAttribute("tweet", repoTweet.findOne(idt));
		model.addAttribute("currentUser", repoUser.findOne(id));
		return "tweetDetails";
	}
	
	/**
	 * Method mapping and processing of current (host) user with given {id} request to display tweets of (guest) 
	 * user of given id {idg}
	 * @param id
	 * @param idc
	 * @param model
	 * @return page with tweets of the selected guest user 
	 */
	@Transactional
	@GetMapping("/{id}/guestTweets/{idg}")
	public String guestTweets(@PathVariable Long id, @PathVariable Long idg, Model model) {
		User guestUser = repoUser.findOne(idg);
		User hostUser = repoUser.findOne(id);
		Hibernate.initialize(guestUser.getTweets());
		model.addAttribute("guestTweets", repoTweet.findByUser(guestUser));
		model.addAttribute("currentUser", hostUser);
		model.addAttribute("guestUser", guestUser);
		return "guestTweets";
	}
	/**
	 * Method mapping and processing of current (host) user with given {id} request to display tweets of 
	 * all followed (guest) users
	 * @param id
	 * @param model
	 * @return page with all tweets of all users followed by current (host) user
	 */
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

	/**
	 * Method mapping current (host) user with given {id} request to post a comment
	 * to tweet with given id {idt}
	 * 
	 * @param id
	 * @param idt
	 * @param model
	 * @return page with all tweets of all users followed by current (host) user 
	 * with new comment input form 
	 */
		
	@Transactional
	@GetMapping("/{id}/comment/{idt}/post")
	public String addComment(@PathVariable Long id,@PathVariable Long idt, Model model) {
		Comment comment = new Comment();
		
		List<Tweet> followedTweets = defineFollowedTweets(id);
		User hostUser = initializeTweets(id, followedTweets);
		
		model.addAttribute("guestTweets", followedTweets);
		model.addAttribute("currentUser", hostUser);
		model.addAttribute("displayNumber", idt);
		model.addAttribute("inputDisplayNumber", idt);
		model.addAttribute("comment", comment);
		return "allGuestsTweets";
	}

	/**
	 * Method processing incl. validation of {comment} entered by user of given {id} 
	 * to post input form regarding tweet of given id {idt}.
 	 * !!!!! To be completed with saving record to database verification !!!!!
	 * 
	 * @param id
	 * @param idt
	 * @param comment
	 * @param result
	 * @param model
	 * @return comment input form with named errors in case of incorrect input or page with all tweets of all users followed by current (host) user 
	 * with new comment input form 
	 */
	
	@Transactional
	@PostMapping("/{id}/comment/{idt}/post")
	public String addCommentPost(@PathVariable Long id, @PathVariable Long idt, 
					@Valid Comment comment, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.err.println(result);
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
	
	/**
	 * Method mapping current (host) user with given {id} request to show comments
	 * to tweet with given id {idt}
	 * @param id
	 * @param idt
	 * @param model
	 * @return page with all tweets of all users followed by current (host) user 
	 * with all comments for tweet of given id {idt} 
	 */
	
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
		followedUsers.add(repoUser.getOne(id));
		List<Tweet> followedTweets = 
				repoTweet.findTweetsOfUsersFollowedByMeOrderDesc(followedUsers);
		return followedTweets;
	}
		
}
