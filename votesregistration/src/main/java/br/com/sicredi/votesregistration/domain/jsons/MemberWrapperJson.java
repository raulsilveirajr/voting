package br.com.sicredi.votesregistration.domain.jsons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberWrapperJson {
	private String status;
	// ABLE_TO_VOTE
	// UNABLE_TO_VOTE
}
