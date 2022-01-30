package br.com.sicredi.votescount.domain.jsons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotescountJson {

	private String id;
	private long votesYes;
	private long votesNo;
}
