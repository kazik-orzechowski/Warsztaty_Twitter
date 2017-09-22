package pl.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.twitter.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	List<Contact> findAllByHostId(Long id);
	List<Contact> findAllByGuestId(Long id);
	Contact findOneByHostIdAndGuestId (Long idh, Long idg);

}
