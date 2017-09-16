package pl.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.twitter.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findOneById (Long id);
}
