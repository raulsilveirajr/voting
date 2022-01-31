package br.com.sicredi.issue.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sicredi.issue.domain.dtos.IssueDTO;
import br.com.sicredi.issue.domain.entities.IssueEntity;
import br.com.sicredi.issue.domain.services.IssueService;
import br.com.sicredi.issue.domain.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/issues")
public class IssueResource {

	@Autowired
	private IssueService issueService;

	public IssueResource(IssueService issueService) {
		super();
		this.issueService = issueService;
	}

	@GetMapping("/")
	public ResponseEntity<List<IssueDTO>> findAll() {
		List<IssueDTO> issues = issueService
				.findAll()
				.stream()
				.map(issue -> new IssueDTO(issue))
				.collect(Collectors.toList());

		if (issues.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(issues);
	}

	@GetMapping("/{id}")
	public ResponseEntity<IssueDTO> findById(@PathVariable String id) {
		try {
			IssueEntity issueEntity = issueService.findById(id);
			return ResponseEntity.ok().body(new IssueDTO(issueEntity));
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	public ResponseEntity<Void> insert(@RequestBody IssueDTO issueDTO) {
		IssueEntity issueEntity = issueService.fromDTO(issueDTO);
		issueEntity = issueService.insert(issueEntity);
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(issueEntity.getId())
			.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody IssueDTO issueDTO) {
		try {
			issueService.findById(id);

			IssueEntity issueEntity = issueService.fromDTO(issueDTO);

			issueEntity.setId(id);
			issueEntity = issueService.update(issueEntity);
			return ResponseEntity.noContent().build();
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}/start")
	public ResponseEntity<Void> startVotation(@PathVariable String id, @RequestBody Long minutes) {
		issueService.startVotation(id, minutes);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/results")
	public ResponseEntity<IssueDTO> getResults(@PathVariable String id) {
		try {
			IssueEntity issueEntity = issueService.getResults(id);

			return ResponseEntity.ok().body(new IssueDTO(issueEntity));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("IssueResource.getResults >>> exception >>> " + e.getMessage());
		}
		return ResponseEntity.notFound().build();
	}
}
