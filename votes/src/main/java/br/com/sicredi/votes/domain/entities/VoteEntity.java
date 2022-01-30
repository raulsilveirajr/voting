package br.com.sicredi.votes.domain.entities;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class VoteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cpf;
	private String issueId;
	private String instantVote;
	private String vote;
}
