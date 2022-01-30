package br.com.sicredi.votes.domain.dtos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.sicredi.votes.domain.entities.VoteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String issueId;
	private String cpf;
	private String instantVote;
	private String vote;

	public VoteDTO(VoteEntity voteEntity) {
		cpf = voteEntity.getCpf();
		issueId = voteEntity.getIssueId();
		instantVote = voteEntity.getInstantVote();
		vote = voteEntity.getVote();
	}
}
