package pl.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.twitter.entity.Tweet;
import pl.twitter.entity.User;

/**
 * Tweet Jpa Repository
 * 
 * @author kaz
 *
 */

public interface TweetRepository extends JpaRepository<Tweet, Long> {

	/**
	 * Tweet search method
	 * 
	 * @param id
	 * @return tweets posted by user with given id
	 */
	@Query("SELECT t FROM Tweet  t WHERE user.id = ?1")
	List<Tweet> findByUserId(Long id);

	/**
	 * Tweet search method
	 * 
	 * @param user
	 * @return tweets of given user
	 */
	@Query("SELECT t FROM Tweet  t WHERE user = ?1")
	List<Tweet> findByUser(User user);

	/**
	 * Tweet search method
	 * 
	 * @param substring
	 * @return tweets that contain given substring
	 */
	@Query(value = "SELECT t FROM Tweet t WHERE title LIKE %?1% ORDER BY created desc ")
	List<Tweet> findByTitleAndSort(String substring);

	/**
	 * Tweet search method
	 * 
	 * @param followedUsers
	 * @return tweets posted by given list of users
	 */
	@Query("SELECT t FROM Tweet t WHERE t.user IN (:guests) ORDER BY created DESC")
	List<Tweet> findTweetsOfUsersFollowedByMeOrderDesc(@Param("guests") List<User> followedUsers);

	/**
	 * Identifies id used of the last tweet created in comments table in twitter
	 * database
	 * 
	 * @return id value of the last tweet
	 */
	Tweet findTop1ByOrderByIdDesc();

}
