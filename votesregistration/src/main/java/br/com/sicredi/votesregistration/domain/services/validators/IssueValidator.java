package br.com.sicredi.votesregistration.domain.services.validators;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sicredi.votesregistration.common.parsers.InstantParser;
import br.com.sicredi.votesregistration.domain.dtos.IssueDTO;
import br.com.sicredi.votesregistration.domain.dtos.VoteDTO;
import br.com.sicredi.votesregistration.domain.services.requests.IssueRequestService;

@Component
public class IssueValidator {

	@Autowired
	private IssueRequestService issueRequestService;

	public boolean openToVote(VoteDTO voteDTO) {
        IssueDTO issueDTO = issueRequestService.execute(voteDTO.getIssueId());

        if ( (issueDTO.getId() == null) 
        		|| (issueDTO.getId().isEmpty()) 
        		|| (issueDTO.getStartVotationAt() == null) 
        		|| (issueDTO.getEndVotationAt() == null) ) {
        	return false;
		}
        if (!inTime(issueDTO, voteDTO)) {
        	return false;
		}
		return true;
	}
	
	public boolean inTime(IssueDTO issueDTO, VoteDTO voteDTO) {
		Instant voteInstant = InstantParser.toInstant(voteDTO.getInstantVote());
		return issueDTO.getStartVotationAt().isBefore(voteInstant)
				&& issueDTO.getEndVotationAt().isAfter(voteInstant);
	}
}
