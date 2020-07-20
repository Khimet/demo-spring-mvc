package dev.hotel.repository;

import java.awt.print.Pageable;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.hotel.entite.Client;

/**
 * @author Khalil HIMET
 *
 */
@Repository
public interface ClientsRepository extends JpaRepository<Client, UUID> {
	
	//Page<Client> findAll(Pageable pageable);

}
