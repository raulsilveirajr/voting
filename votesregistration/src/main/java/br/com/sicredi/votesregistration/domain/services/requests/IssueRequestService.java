package br.com.sicredi.votesregistration.domain.services.requests;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.sicredi.votesregistration.domain.dtos.IssueDTO;
import br.com.sicredi.votesregistration.domain.feign.IssueClient;
import br.com.sicredi.votesregistration.domain.jsons.IssueJson;
import feign.Feign;
import feign.gson.GsonDecoder;

@Service
public class IssueRequestService {

	@Cacheable(value="issue")
	public IssueDTO execute(String id) {
 		try {
			IssueClient issueClient = Feign.builder()
					.decoder(new GsonDecoder())
					.target(IssueClient.class, "http://localhost:8089");

			IssueJson issueJson = issueClient.getIssue(id);
			return new IssueDTO(issueJson);
		} catch (Exception e) {
			// e.printStackTrace();
			return new IssueDTO();
		}
	}
}
