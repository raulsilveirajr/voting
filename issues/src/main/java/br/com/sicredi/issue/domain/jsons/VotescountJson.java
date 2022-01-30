package br.com.sicredi.issue.domain.jsons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotescountJson {

	private String id;
	private Long votesYes;
	private Long votesNo;
}