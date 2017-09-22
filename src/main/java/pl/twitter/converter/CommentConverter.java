package pl.twitter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.twitter.entity.Comment;
import pl.twitter.repository.CommentRepository;

public class CommentConverter implements Converter<String, Comment> {

	@Autowired
	CommentRepository repoComment;

	@Override
	public Comment convert(String source) {
		return repoComment.findOne(Long.parseLong(source));

	}


}
