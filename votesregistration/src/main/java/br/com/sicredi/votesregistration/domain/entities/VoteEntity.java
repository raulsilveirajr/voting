package br.com.sicredi.votesregistration.domain.entities;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.sicredi.votesregistration.domain.dtos.VoteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "votes")
public class VoteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private String id;

	private String issueId;
	private String cpf;
	private String instantVote;
	private String vote;
	
	public VoteEntity(VoteDTO voteDTO) {
		id = voteDTO.getId();
		issueId = voteDTO.getIssueId();
		cpf = voteDTO.getCpf();
		instantVote = voteDTO.getInstantVote();
		vote = voteDTO.getVote();
	}
	
}
