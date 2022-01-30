package br.com.sicredi.votes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.votes.domain.dtos.VoteDTO;
import br.com.sicredi.votes.domain.services.VoteService;

@RestController
@RequestMapping(value="/votes")
public class VoteResource {

	@Autowired
	private VoteService voteService;
	
	@PostMapping("/")
	public ResponseEntity<String> saveVote(@RequestBody VoteDTO voteDTO) {
		voteService.registerVote(voteDTO);
		return ResponseEntity.ok().body("Seu voto foi registrado e será processado em seguida! Obrigado por participar da votação.");
	}

}
