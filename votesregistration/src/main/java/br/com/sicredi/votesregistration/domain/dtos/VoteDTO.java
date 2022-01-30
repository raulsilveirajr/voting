package br.com.sicredi.votesregistration.domain.dtos;

import java.io.Serializable;

import br.com.sicredi.votesregistration.domain.entities.VoteEntity;
import br.com.sicredi.votesregistration.domain.jsons.VoteJson;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String issueId;
	private String cpf;
	private String instantVote;
	private String vote;

	public VoteDTO(VoteEntity voteEntity) {
		id = voteEntity.getId();
		issueId = voteEntity.getIssueId();
		cpf = voteEntity.getCpf();
		instantVote = voteEntity.getInstantVote();
		vote = voteEntity.getVote();
	}

	public VoteDTO(VoteJson voteJson) {
		issueId = voteJson.getIssueId();
		cpf = voteJson.getCpf();
		instantVote = voteJson.getInstantVote();
		vote = voteJson.getVote();
	}
}
