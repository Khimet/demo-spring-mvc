package dev.hotel.repository;

import java.awt.print.Pageable;
import java.util.UUID;

import org.hibernate.id.UUIDGenerator;
import org.hibernate.type.UUIDCharType;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.hotel.entite.Client;

/**
 * @author Khalil HIMET
 *
 */
@Repository
public interface ClientsRepository extends JpaRepository<Client, UUID> {
	
	//Page<Client> findAll(Pageable pageable);
	
//	@Query("select c from Client c where c.uuid = ?1")
//	Client findClientbyUUID(UUID uuid);

}
