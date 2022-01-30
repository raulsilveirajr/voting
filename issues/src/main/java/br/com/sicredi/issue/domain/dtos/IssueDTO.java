package br.com.sicredi.issue.domain.dtos;

import java.io.Serializable;
import java.time.Instant;

import br.com.sicredi.issue.domain.entities.IssueEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IssueDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String description;
	private Instant createdAt;
	private Instant updatedAt;
	private Instant startVotationAt;
	private Instant endVotationAt;
	private Integer yesVotes;
	private Integer noVotes;

	public IssueDTO(IssueEntity issueEntity) {
		id = issueEntity.getId();
		name = issueEntity.getName();
		description = issueEntity.getDescription();
		createdAt = issueEntity.getCreatedAt();
		updatedAt = issueEntity.getUpdatedAt();
		startVotationAt = issueEntity.getStartVotationAt();
		endVotationAt = issueEntity.getEndVotationAt();
		yesVotes = issueEntity.getYesVotes();
		noVotes = issueEntity.getNoVotes();
	}
}
