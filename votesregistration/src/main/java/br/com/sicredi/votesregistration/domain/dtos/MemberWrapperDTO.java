package br.com.sicredi.votesregistration.domain.dtos;

import java.io.Serializable;

import br.com.sicredi.votesregistration.domain.entities.VoteEntity;
import br.com.sicredi.votesregistration.domain.jsons.IssueJson;
import br.com.sicredi.votesregistration.domain.jsons.MemberWrapperJson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberWrapperDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String status;

	public MemberWrapperDTO(MemberWrapperJson memberWrapperJson) {
		status = memberWrapperJson.getStatus();
	}
}
