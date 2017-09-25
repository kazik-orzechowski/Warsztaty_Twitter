package pl.twitter.converter;

import org.springframework.beans.factory.annotation.Autowired;

import pl.twitter.entity.User;
import pl.twitter.repository.UserRepository;

import org.springframework.core.convert.converter.Converter;

/**
 * User converter
 * @author kaz
 *
 */
public class UserConverter implements Converter<String, User> {

	/**
	 * Jpa repository reference to User class
	 */
	@Autowired
	UserRepository repoUser;

	/**
	 * Method converting string representing user id into a Comment object 
	 */
	@Override
	public User convert(String source) {
		return repoUser.findOne(Long.parseLong(source));

	}

}
