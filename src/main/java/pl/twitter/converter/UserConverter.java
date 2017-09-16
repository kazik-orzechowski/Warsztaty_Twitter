package pl.twitter.converter;

import org.springframework.beans.factory.annotation.Autowired;

import pl.twitter.entity.User;
import pl.twitter.repository.UserRepository;

import org.springframework.core.convert.converter.Converter;

public class UserConverter implements Converter<String, User> {

	@Autowired
	UserRepository repoUser;

	@Override
	public User convert(String source) {
		return repoUser.findOne(Long.parseLong(source));

	}

}
