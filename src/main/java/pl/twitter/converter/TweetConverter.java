package pl.twitter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.twitter.entity.Tweet;
import pl.twitter.repository.TweetRepository;

public class TweetConverter implements Converter<String, Tweet> {

	@Autowired
	TweetRepository repoTweet;

	@Override
	public Tweet convert(String source) {
		return repoTweet.findOne(Long.parseLong(source));

	}

}
