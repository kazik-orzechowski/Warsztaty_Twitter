package pl.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.twitter.entity.Comment;
import pl.twitter.entity.Tweet;
import pl.twitter.entity.User;


public interface CommentRepository extends JpaRepository<Comment, Long> {

//	@Query("SELECT c FROM Comment t WHERE user.id = ?1")
//	   List<Comment> findByUserId(Long id);   
//	
//	@Query("SELECT c FROM Comment c WHERE user = ?1")
//	 List<Comment> findByUser(User user);   
	
	
}
