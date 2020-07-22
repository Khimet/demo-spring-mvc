package dev.hotel.controller;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("clients")
public class ClientsRequests {
	
	private ClientsRepository clientsRepository;

	/** Constructeur
	 * @param clientsRepository
	 */
	public ClientsRequests(ClientsRepository clientsRepository) {
		super();
		this.clientsRepository = clientsRepository;
	}
	
	@GetMapping("pagination")
	public List<Client> GetClientsPage(
			@RequestParam("start") Integer page,
			@RequestParam("size") Integer size){
		
		
		
		PageRequest paging = PageRequest.of(page, size);
		
		Page<Client> clients = clientsRepository.findAll(paging);
		
		List<Client> resultats = clients.getContent();
		
		return resultats;
		
	}
	
	@GetMapping("/UUID/{uuidString}")
	public ResponseEntity<?> GetClientFromUUID(@PathVariable String uuidString) {
		
		Pattern p = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
		
		boolean ValidUUID = p.matcher(uuidString).matches();
		
		if (ValidUUID) {
			
			UUID uuid = UUID.fromString(uuidString);
			
			Optional<Client> client = clientsRepository.findById(uuid);
			
			if (client.isPresent()) {
				
				return ResponseEntity.status(HttpStatus.ACCEPTED)
						.body(client.get());
			} else {
				
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("l'UUID entré ne correspond pas à un client de la base de données");
			}
			
		} else {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("l'UUID entré n'est pas valide");
		}
		
	}
	
	
	
	

}
