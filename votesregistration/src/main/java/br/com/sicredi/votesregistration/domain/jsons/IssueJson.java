package br.com.sicredi.votesregistration.domain.jsons;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueJson {
	private String id;
	private String name;
	private String description;
	private String createdAt;
	private String updatedAt;
	private String startVotationAt;
	private String endVotationAt;
	private String yesVotes;
	private String noVotes;

}
