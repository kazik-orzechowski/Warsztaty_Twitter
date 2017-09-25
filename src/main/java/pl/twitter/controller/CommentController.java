package pl.twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Comment controller. 
 * All actions concerning comments are now performed via TweetController 
 * This controller will be used for possible admin actions regarding comments 
 * in bulk (e.g. listing, deleting)
 * @author kaz
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	

	}
