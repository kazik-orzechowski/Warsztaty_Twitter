package pl.twitter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.twitter.entity.Contact;
import pl.twitter.entity.GuestDTO;
import pl.twitter.entity.User;
import pl.twitter.repository.ContactRepository;
import pl.twitter.repository.UserRepository;

@Controller
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	UserRepository repoUser;

	@Autowired
	ContactRepository repoContact;

	@Transactional
	@RequestMapping("/{id}/all")
	public String allUsers(@PathVariable Long id, Model model) {

		List<User> userList = repoUser.findAll();
		User host = repoUser.getOne(id);
		userList.remove(host);

		for (User guest : userList) {
			Hibernate.initialize(guest.getContactsGuest());
			Hibernate.initialize(guest.getContactsHost());
			if (repoContact.findOneByHostIdAndGuestId(id, guest.getId()) == null) {
				Contact addedContact = new Contact(host,guest);
				repoContact.save(addedContact);
			}
		}
		// host user contact list
		List<Contact> guests = repoContact.findAllByHostId(id);
		List<GuestDTO> guestsData = new ArrayList<GuestDTO>();

		for (Contact guest : guests) {
			String statusName;
			if (guest.getStatus() == 1) {
				statusName = "not followed";
			} else if (guest.getStatus() == 2) {
				statusName = "followed";
			} else {
				statusName = "banned";
			}

			GuestDTO guestDTO = new GuestDTO(guest.getGuest().getId(), guest.getGuest().getUsername(), statusName);
			if (guest.getGuest().getId() != host.getId()) {
				guestsData.add(guestDTO);
			}
		}

		model.addAttribute("hostContacts", guestsData);
		model.addAttribute("currentUser", host);
		System.err.println(host.toString());

		return "contacts";
	}

	
	 @GetMapping("/{id}/follow/{idc}")
	 public String followContact (@PathVariable Long id, @PathVariable Long idc,
	 Model model) {
	
	 Contact contact = repoContact.findOneByHostIdAndGuestId(id, idc);
	 contact.setStatus(2);
	 repoContact.save(contact);
	 	
	 return "redirect: /Twitter/contact/"+id+"/all";
	 }
	 
	 @GetMapping("/{id}/ban/{idc}")
	 public String banContact (@PathVariable Long id, @PathVariable Long idc,
	 Model model) {
	
	 Contact contact = repoContact.findOneByHostIdAndGuestId(id, idc);
	 contact.setStatus(3);
	 repoContact.save(contact);
	 	
	 return "redirect: /Twitter/contact/"+id+"/all";
	 }
	 
	 
	 @GetMapping("/{id}/stopFollow/{idc}")
	 public String stopFollowContact (@PathVariable Long id, @PathVariable Long idc,
	 Model model) {
	
	 Contact contact = repoContact.findOneByHostIdAndGuestId(id, idc);
	 contact.setStatus(1);
	 repoContact.save(contact);
	 	
	 return "redirect: /Twitter/contact/"+id+"/all";
	 }
	 
	 
	//
	// @PostMapping("/{id}/edit")
	// public String editUserPost(@Valid User user, @PathVariable Long id,
	// BindingResult result, Model model) {
	// if (result.hasErrors()) {
	// System.out.println(result);
	// return "editUser";
	// }
	// repoUser.save(user);
	//
	// model.addAttribute("eventType", repoEventType.findAll());
	//
	// if (repoUser.findOneById(id).getUserName().equals("admin")) {
	// model.addAttribute("events", repoEvent.findAll());
	// return "users";
	// } else {
	// model.addAttribute("events", repoEvent.findAllBySeriesUserId(id));
	// return "userPanel";
	// }
	//
	// }
	//
	// @GetMapping("/add")
	// public String addUser(Model model) {
	//
	// if(!SessionValidation.isSessionAdmin()) {
	// return "main";
	// }
	//
	// User user = new User();
	// model.addAttribute("user", user);
	// return "signup";
	// }
	//
	// @PostMapping("/add")
	// public String addUserPost(@Valid User user, BindingResult result, Model
	// model) {
	// if (result.hasErrors()) {
	// return "signup";
	// }
	// repoUser.save(user);
	// model.addAttribute("user", user);
	// model.addAttribute("users", repoUser.findAll());
	// return "users";
	//
	// }
	//
	//
	//
	//

}
