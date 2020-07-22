package dev.hotel.controller;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.dto.ClientDto;
import dev.hotel.entite.Client;
import dev.hotel.repository.ClientsRepository;
import dev.hotel.service.ClientService;

/**
 * @author Khalil HIMET
 *
 */
@RestController
@RequestMapping("clients")
public class ClientsController {
	
	private ClientService clientService;

	/** Constructeur
	 * @param clientsRepository
	 */
	public ClientsController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}
	
	@GetMapping("pagination")
	public ResponseEntity<?> GetClientsPage(
			@RequestParam("start") Integer page,
			@RequestParam("size") Integer size){
		
		if ( size == null || page == null || page < 0 || size <= 0 ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
		}
		
		//PageRequest paging = PageRequest.of(page, size);
		
		Page<Client> clients = clientService.getClientsPage(page, size);
		
		//List<Client> resultats = clients.getContent();
		
		//return resultats;
		
		return ResponseEntity.status(HttpStatus.OK).body(clients.getContent());
		
		
		
	}
	
	@GetMapping("/UUID/{uuidString}")
	public ResponseEntity<?> GetClientFromUUID(@PathVariable String uuidString) {
		
		Pattern p = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
		
		boolean ValidUUID = p.matcher(uuidString).matches();
		
		if (ValidUUID) {
			
			UUID uuid = UUID.fromString(uuidString);
			
			Optional<Client> client = clientService.findClientFromUUID(uuid);
			
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
	
	@PostMapping
	public ResponseEntity<?> postClients(@RequestBody @Valid ClientDto client, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom et le prenom doivent etre entrés.");
		}	
		
		Client clientBase = clientService.creerClient(client.getNom(), client.getPrenoms());
		return ResponseEntity.status(HttpStatus.OK).body(clientBase);
	}
	
	
	
	

}
