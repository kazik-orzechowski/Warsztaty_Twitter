package pl.twitter.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.twitter.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findOneById (Long id);
	
//	@Query ("SELECT u FROM User u WHERE u.contactsHost.status = (:status)"
//			+ " AND u.contactsHost.host.id = (:hostId)")
//	List<User> findAllGuestsByHostIdAndGuestStatus
//				(@Param("hostId")Long hostID, @Param("status") Integer status);

	
	
}
