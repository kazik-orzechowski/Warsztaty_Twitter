package pl.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.twitter.entity.Contact;
import pl.twitter.entity.Tweet;
import pl.twitter.entity.User;


public interface TweetRepository extends JpaRepository<Tweet, Long> {

	@Query("SELECT t FROM Tweet  t WHERE user.id = ?1")
	   	List<Tweet> findByUserId(Long id);   
	
	@Query("SELECT t FROM Tweet  t WHERE user = ?1")
	 	List<Tweet> findByUser(User user);   
	
	@Query(value = "SELECT t FROM Tweet t WHERE title LIKE %?1% ORDER BY created desc ")
	    List<Tweet> findByTitleAndSort (String substring);
	
	@Query ("SELECT t FROM Tweet t WHERE t.user IN (:guests) ORDER BY created DESC")
		List<Tweet> findTweetsOfUsersFollowedByMeOrderDesc 
						(@Param("guests")List<User> followedUsers);

	Tweet findTop1ByOrderByIdDesc ();
	
}
