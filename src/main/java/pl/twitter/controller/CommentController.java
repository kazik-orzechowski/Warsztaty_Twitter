package pl.twitter.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import pl.twitter.entity.Tweet;
import pl.twitter.entity.User;
import pl.twitter.repository.ContactRepository;
import pl.twitter.repository.TweetRepository;
import pl.twitter.repository.UserRepository;



@Controller
@RequestMapping("/comment")
public class CommentController {
	

	}
