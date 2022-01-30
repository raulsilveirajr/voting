package br.com.sicredi.votescount.domain.dtos;

import java.io.Serializable;

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

//	public VoteDTO(VoteEntity voteEntity) {
//		id = voteEntity.getId();
//		issueId = voteEntity.getIssueId();
//		cpf = voteEntity.getCpf();
//		instantVote = voteEntity.getInstantVote();
//		vote = voteEntity.getVote();
//	}

}