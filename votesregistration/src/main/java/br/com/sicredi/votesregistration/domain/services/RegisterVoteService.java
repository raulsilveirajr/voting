package br.com.sicredi.votesregistration.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votesregistration.domain.dtos.VoteDTO;
import br.com.sicredi.votesregistration.domain.entities.VoteEntity;
import br.com.sicredi.votesregistration.domain.services.validators.IssueValidator;
import br.com.sicredi.votesregistration.domain.services.validators.MemberValidator;

@Service
public class RegisterVoteService {

	@Autowired
	private IssueValidator issueValidator;
	
	@Autowired
	private MemberValidator memberValidator;
	
	@Autowired
	private VoteService voteService;

	public void execute(VoteDTO voteDTO) {
        try {
        	if (!issueValidator.openToVote(voteDTO)) {
        		return;
        	}
        	
        	if (!memberValidator.enableToVote(voteDTO)) {
        		return;
        	}
        	VoteEntity voteEntity = new VoteEntity(voteDTO);
        	voteEntity = voteService.insert(voteEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;
	}
}
