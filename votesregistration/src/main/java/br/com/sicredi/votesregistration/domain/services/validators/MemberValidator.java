package br.com.sicredi.votesregistration.domain.services.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sicredi.votesregistration.domain.dtos.MemberWrapperDTO;
import br.com.sicredi.votesregistration.domain.dtos.VoteDTO;
import br.com.sicredi.votesregistration.domain.entities.VoteEntity;
import br.com.sicredi.votesregistration.domain.services.VoteService;
import br.com.sicredi.votesregistration.domain.services.requests.MemberWrapperRequestService;

@Component
public class MemberValidator {

	@Autowired
	private VoteService voteService;
	
	@Autowired
	private MemberWrapperRequestService memberWrapperRequestService;

	public boolean enableToVote(VoteDTO voteDTO) {
		List<VoteEntity> listVoteEntity = voteService.findByIssueIdAndCpf(voteDTO.getIssueId(), voteDTO.getCpf());
		if (!listVoteEntity.isEmpty()) {
			// Vote registred before
			return false;
		}
		
		try {
			MemberWrapperDTO memberWrapperDTO = memberWrapperRequestService.execute(voteDTO.getCpf());
			if (memberWrapperDTO.getStatus()==null) {
				// Invalid or not found CPF
				return false;
			}
			if (!memberWrapperDTO.getStatus().equals("ABLE_TO_VOTE")) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
