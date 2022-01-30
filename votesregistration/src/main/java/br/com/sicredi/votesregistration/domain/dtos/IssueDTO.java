package br.com.sicredi.votesregistration.domain.dtos;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sicredi.votesregistration.common.parsers.InstantParser;
import br.com.sicredi.votesregistration.domain.jsons.IssueJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Autowired
	private InstantParser instantParser; 
	
	private String id;
	private String name;
	private String description;
	private Instant createdAt;
	private Instant updatedAt;
	private Instant startVotationAt;
	private Instant endVotationAt;
	private Integer yesVotes;
	private Integer noVotes;

	public IssueDTO(IssueJson issueJson) {
		id = issueJson.getId();
		name = issueJson.getName();
		description = issueJson.getDescription();
		createdAt = instantParser.toInstant(issueJson.getCreatedAt());
		updatedAt = instantParser.toInstant(issueJson.getUpdatedAt());
		startVotationAt = instantParser.toInstant(issueJson.getStartVotationAt());
		endVotationAt = instantParser.toInstant(issueJson.getEndVotationAt());
	}
}
