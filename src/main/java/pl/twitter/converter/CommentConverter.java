package pl.twitter.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.twitter.entity.Comment;
import pl.twitter.repository.CommentRepository;

/**
 * Comment converter 
 * @author kaz
 *
 */

public class CommentConverter implements Converter<String, Comment> {
	
	/**
	 * Jpa repository reference to Comment class
	 */
	@Autowired
	CommentRepository repoComment;
	
	/**
	 * Method converting string representing comment id into a Comment object 
	 */
	@Override
	public Comment convert(String source) {
		return repoComment.findOne(Long.parseLong(source));

	}


}
