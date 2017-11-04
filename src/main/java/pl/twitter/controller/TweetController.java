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
 * Main Twitter controller responsible to process user's requests concerning
 * tweets and comments
 * 
 * @author kaz
 *
 */

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

	/**
	 * Method mapping a request of the current user and preparing the display of
	 * this user's home page containing tweet input form and list of user's own
	 * tweets
	 * 
	 * @param id
	 *            - current (host) user id
	 * @param model
	 *            - Model class object used to pass attributes to target view
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
	 * Method mapping an input request of the current user regarding a new tweet
	 * title and text, including input data validation, setting an id, a date and
	 * user for the new tweet
	 * 
	 * !!!!! To be completed with saving record to database verification !!!!!
	 * 
	 * @param id
	 *            - current (host) user id
	 * @param model
	 *            - Model class object used to pass attributes to target view
	 * @param tweet
	 *            - tweet binded object from the post form
	 * @param result
	 *            - binding result data
	 * @return tweet input form on user home page (to be amended) or user home page
	 *         with new tweet added to the displayed list
	 */

	@Transactional
	@PostMapping("/{id}/add")
	public String addTweet2(@PathVariable Long id, @Valid Tweet tweet, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.err.println(result);
			return "tweets";
		}
		Hibernate.initialize(repoUser.getOne(id).getTweets());
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
	 * Method mapping user request with given {id} concerning removal of tweet with
	 * given {idt} and deleting this tweet !!!!! To be completed with saving record
	 * to database verification !!!!!
	 * 
	 * @param id
	 *            - current (host) user id
	 * @param model
	 *            - Model class object used to pass attributes to target view
	 * @param tweetId
	 *            - id of the deleted tweet
	 * @return user's home page
	 */

	@GetMapping("/{id}/delete/{idt}")
	public String delTweet(@PathVariable Long id, @PathVariable Long idt, Model model) {
		repoTweet.delete(idt);
		return "redirect: /Twitter/tweet/" + id + "/add";
	}

	/**
	 * Method mapping user with given {id} request to display details of tweet with
	 * given {idt}
	 * 
	 * @param id
	 *            - current (host) user id
	 * @param model
	 *            - Model class object used to pass attributes to target view
	 * @param tweetId
	 *            - id of the tweet to be displayed
	 * @return tweet datails page
	 */
	@GetMapping("/{id}/details/{idt}")
	public String detailedTweet(@PathVariable Long id, @PathVariable Long idt, Model model) {
		model.addAttribute("tweet", repoTweet.findOne(idt));
		model.addAttribute("currentUser", repoUser.findOne(id));
		return "tweetDetails";
	}

	/**
	 * Maps and processes current (host) user's request to display
	 * tweets of guest user
	 * 
	 * @param id
	 *            - current (host) user id
	 * @param model
	 *            - Model class object used to pass attributes to target view
	 * @param idc
	 *            - id of the guest (target) user
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
	 * Maps and processes current (host) user's request to display
	 * tweets of all the followed guest users all followed (guest) users
	 * 
	 * @param id
	 *            - current (host) user id
	 * @param model
	 *            - Model class object used to pass attributes to target view
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
	 * Maps current (host) user's request to post a comment
	 * to the target tweet 
	 * 
	 * @param id - current (host) user id
	 * @param model - Model class object used to pass attributes to target view
	 * @param idt - id of the target tweet
	 * @return page with all tweets of all users followed by current (host) user
	 *         with new comment input form
	 */

	@Transactional
	@GetMapping("/{id}/comment/{idt}/post")
	public String addComment(@PathVariable Long id, @PathVariable Long idt, Model model) {
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
	 * Processes and validates the comment entered by user to post input form regarding the target tweet !!!!! To be completed
	 * with saving record to database verification !!!!!
	 * 
	 * @param id - current (host) user id
	 * @param model - Model class object used to pass attributes to target view
	 * @param idt - id of the target tweet
	 * @param comment - binded comment object 
	 * @param result - binding result
	 * @return comment input form with named errors in case of incorrect input or
	 *         page with all tweets of all users followed by current (host) user
	 *         with new comment input form
	 */

	@Transactional
	@PostMapping("/{id}/comment/{idt}/post")
	public String addCommentPost(@PathVariable Long id, @PathVariable Long idt, @Valid Comment comment,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.err.println(result);
			return "allGuestsTweets";
		}
		Hibernate.initialize(repoUser.getOne(id).getTweets());
		Date date = new Date();
		comment.setCreated(date);
		comment.setTweet(repoTweet.getOne(idt));
		comment.setUser(repoUser.getOne(id));
		if (repoComment.findTop1ByOrderByIdDesc() == null) {
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
	 * 
	 * @param id - current (host) user id
	 * @param idt - id of the target tweet
	 * @param model - Model class object used to pass attributes to target view
	 * @return page with all tweets of all users followed by current (host) user
	 *         with all comments for tweet of given id {idt}
	 */

	@Transactional
	@GetMapping("/{id}/comment/{idt}/show")
	public String showComments(@PathVariable Long id, @PathVariable Long idt, Model model) {

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
	 * Initializes comments for each tweet
	 * 
	 * @param id - current user's id
	 * @param followedTweets - list of tweets followed by the current user
	 * @return host user with tweets initialized
	 */

	private User initializeTweets(Long id, List<Tweet> followedTweets) {
		User hostUser = repoUser.findOne(id);
		for (Tweet tweet : followedTweets) {
			Hibernate.initialize(tweet.getComment());
		}
		return hostUser;
	}

	/**
	 * Method defining list of tweets to be displayed
	 * 
	 * @param id - current user's id
	 * @return list of all tweets of all followed (guest) users followed by host
	 *         user
	 */
	private List<Tweet> defineFollowedTweets(Long id) {
		List<Contact> myFollowedContacts = repoContact.findAllByHostIdAndStatus(id, 2);
		List<User> followedUsers = new ArrayList<>();
		for (Contact contact : myFollowedContacts) {
			followedUsers.add(contact.getGuest());
		}
		followedUsers.add(repoUser.getOne(id));
		List<Tweet> followedTweets = repoTweet.findTweetsOfUsersFollowedByMeOrderDesc(followedUsers);
		return followedTweets;
	}

}
