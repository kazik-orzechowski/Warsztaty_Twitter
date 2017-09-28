package pl.twitter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.twitter.entity.Tweet;
import pl.twitter.repository.TweetRepository;

/**
 * String to Tweet class object converter
 * @author kaz
 *
 */
public class TweetConverter implements Converter<String, Tweet> {

	
	@Autowired
	TweetRepository repoTweet;
	
	/**
	 * Method converting string representing tweet id into a Comment object 
	 */
	@Override
	public Tweet convert(String source) {
		return repoTweet.findOne(Long.parseLong(source));

	}

}
