package pl.twitter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.twitter.entity.Contact;
import pl.twitter.entity.Tweet;
import pl.twitter.repository.ContactRepository;
import pl.twitter.repository.TweetRepository;

/**
 * Contact converter
 * 
 * @author kaz
 *
 */
public class ContactConverter implements Converter<String, Contact> {

	/**
	 * Jpa repository reference to Contact class
	 */
	@Autowired
	ContactRepository repoContact;

	/**
	 * Method converting string representing given contact id into a Contact object
	 */
	@Override
	public Contact convert(String source) {
		return repoContact.findOne(Long.parseLong(source));

	}

}
