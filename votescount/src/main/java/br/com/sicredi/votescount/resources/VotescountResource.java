package br.com.sicredi.votescount.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.votescount.domain.jsons.VotescountJson;
import br.com.sicredi.votescount.services.VotescountService;

@RestController
@RequestMapping(value = "/votescount") 
public class VotescountResource {

	@Autowired
	private VotescountService votescountService;
	
	@GetMapping("/{id}")
	public ResponseEntity<VotescountJson> findById(@PathVariable String id) {
		VotescountJson votescountJson = votescountService.countVotes(id);
		return ResponseEntity.ok().body(votescountJson);
	}
}
