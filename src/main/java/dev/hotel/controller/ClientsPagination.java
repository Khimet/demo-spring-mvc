package dev.hotel.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientsRepository;

/**
 * @author Khalil HIMET
 *
 */
@RestController
@RequestMapping("clientsPage")
public class ClientsPagination {
	
	private ClientsRepository clientsRepository;

	/** Constructeur
	 * @param clientsRepository
	 */
	public ClientsPagination(ClientsRepository clientsRepository) {
		super();
		this.clientsRepository = clientsRepository;
	}
	
	@GetMapping("clientsParams")
	public List<Client> GetClients(
			@RequestParam("start") Integer page,
			@RequestParam("size") Integer size){
		
		
		
		PageRequest paging = PageRequest.of(page, size);
		
		Page<Client> clients = clientsRepository.findAll(paging);
		
		List<Client> resultats = clients.getContent();
		
		return resultats;
		
	}
	
	

}
