package pl.twitter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.twitter.entity.Contact;
import pl.twitter.entity.GuestDTO;
import pl.twitter.entity.User;
import pl.twitter.repository.ContactRepository;
import pl.twitter.repository.UserRepository;

/**
 * Contact controller used to manage all twitter user actions regarding contacts
 * 
 * @author kaz
 *
 */
@Controller
@RequestMapping("/contact")
public class ContactController {

	/**
	 * Jpa repository reference to User class
	 */
	@Autowired
	UserRepository repoUser;
	/**
	 * Jpa repository reference to Contact class
	 */
	@Autowired
	ContactRepository repoContact;

	/**
	 * Method responsible for display user screen containing list of other (guest)
	 * users with menu that makes it possible to set guest user status and see their
	 * tweets
	 * 
	 * @param id
	 * @param model
	 * @return web page contacts.jsp
	 */

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
				Contact addedContact = new Contact(host, guest);
				repoContact.save(addedContact);
			}
		}
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

		return "redirect: /Twitter/contact/" + id + "/all";
	}

	/**
	 * Method that sets a status of guest user (via Contact object) with given id
	 * {idc} to "follow" (value = 2) for host user with given {id} 
	 * !!!!! To be completed with updating record in database verification !!!!!
	 * 
	 * @param id
	 * @param idc
	 * @param model
	 * @return contact page of current user
	 */

	@GetMapping("/{id}/follow/{idc}")
	public String followContact(@PathVariable Long id, @PathVariable Long idc, Model model) {

		Contact contact = repoContact.findOneByHostIdAndGuestId(id, idc);
		contact.setStatus(2);
		repoContact.save(contact);

		return "redirect: /Twitter/contact/" + id + "/all";
	}

	/**
	 * Method that sets a status of guest user (via Contact object) with given id
	 * {idc} to "ban" (value = 3) for host user with given {id} 
	 * !!!!! To be completed with saving record to database verification !!!!!
	 * 
	 * @param id
	 * @param idc
	 * @param model
	 * @return contact page of current user
	 */
	@GetMapping("/{id}/ban/{idc}")
	public String banContact(@PathVariable Long id, @PathVariable Long idc, Model model) {

		Contact contact = repoContact.findOneByHostIdAndGuestId(id, idc);
		contact.setStatus(3);
		repoContact.save(contact);

		return "redirect: /Twitter/contact/" + id + "/all";
	}

	/**
	 * Method that sets a status of guest user (via Contact object) with given id
	 * {idc} to "not follow" (value = 1) for host user with given id {id} 
	 * !!!!! To be completed with saving record to database verification !!!!!
	 * 
	 * @param id
	 * @param idc
	 * @param model
	 * @return contact page of current user
	 */
	@GetMapping("/{id}/stopFollow/{idc}")
	public String stopFollowContact(@PathVariable Long id, @PathVariable Long idc, Model model) {

		Contact contact = repoContact.findOneByHostIdAndGuestId(id, idc);
		contact.setStatus(1);
		repoContact.save(contact);

		return "redirect: /Twitter/contact/" + id + "/all";
	}

}
