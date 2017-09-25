package pl.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.twitter.entity.Contact;

	/**
	 * Contacts Jpa Repository
	 * @author kaz
	 *
	 */

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	/**
	 * Contact search method
	 * @param id
	 * @return all contacts of a host user of given id
	 */
	List<Contact> findAllByHostId(Long id);
	
	/**
	 * Contact search method
	 * @param id
	 * @return all contacts of a guest user of given id
	 */
	List<Contact> findAllByGuestId(Long id);
	
	/**
	 * Contact search method
	 * @param idh
	 * @param idg
	 * @return a contact object defining relationship between host user with given id (idh) 
	 * and guest user with given id (idg)
	 */
	Contact findOneByHostIdAndGuestId (Long idh, Long idg);
	
	
	/**
	 * Contacts search method
	 * @param id
	 * @param status
	 * @return all contacts of host user with given id that match 
	 * status criteria 1 - not followed, 2 - followed, 3 - banned
	 */
	List<Contact> findAllByHostIdAndStatus(Long id, int status);

}
