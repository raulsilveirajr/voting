package br.com.sicredi.votesregistration.domain.jsons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteJson {
	private String issueId;
	private String cpf;
	private String instantVote;
	private String vote;
}
