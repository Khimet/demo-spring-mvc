package dev.hotel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientsRepository;

/**
 * @author Khalil HIMET
 *
 */
@Service
public class ClientService {
	
	/** repo */
	private ClientsRepository clientsRepository;

	/** Getter
	 * @return clientsRepository
	 */
	public ClientsRepository getClientsRepository() {
		return clientsRepository;
	}

	/**
	 * Constructeur
	 * 
	 * @param repo
	 */
	public ClientService(ClientsRepository repo) {
		super();
		this.clientsRepository = repo;
	}
	
	public Page<Client> getClientsPage(Integer start, Integer size){
		
		return clientsRepository.findAll(PageRequest.of(start, size));
	}
	
	public Optional<Client> findClientFromUUID(UUID uuid){
		
		return clientsRepository.findById(uuid);
	}

	@Transactional
	public Client creerClient(String nom, String prenom) {
		Client cl = new Client();
		cl.setNom(nom);
		cl.setPrenoms(prenom);
		return this.clientsRepository.save(cl);

	}

}
