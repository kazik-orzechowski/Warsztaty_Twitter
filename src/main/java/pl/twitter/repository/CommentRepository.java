package pl.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.twitter.entity.Comment;
import pl.twitter.entity.Tweet;
import pl.twitter.entity.User;

/**
 * Comment Jpa Repository
 * 
 * @author kaz
 *
 */

public interface CommentRepository extends JpaRepository<Comment, Long> {

	/**
	 * Generates a list of comments that concern a tweet with a given id
	 * 
	 * @param id
	 * @return list of comments for one tweet of given id
	 */
	List<Comment> findAllByTweetId(Long id);

	/**
	 * Identifies id used of the last comment created in comments table in twitter
	 * database
	 * 
	 * @return id value of the last comment
	 */
	Comment findTop1ByOrderByIdDesc();

}
