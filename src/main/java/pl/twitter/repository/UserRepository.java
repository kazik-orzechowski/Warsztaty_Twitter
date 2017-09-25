package pl.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.twitter.entity.User;

/**
 * User Jpa repository
 * 
 * @author kaz
 *
 */

public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * User search method
	 * 
	 * @param id
	 * @return user with given id
	 */
	User findOneById(Long id);

}
