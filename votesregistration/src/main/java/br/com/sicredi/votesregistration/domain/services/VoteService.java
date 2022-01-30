package br.com.sicredi.votesregistration.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votesregistration.domain.entities.VoteEntity;
import br.com.sicredi.votesregistration.domain.repositories.VoteRepository;

@Service
public class VoteService {

	@Autowired
	private VoteRepository voteRepository;
	
	public List<VoteEntity> findByIssueIdAndCpf(String issueId, String cpf) {
		List<VoteEntity> listVoteEntity =  voteRepository.findByIssueIdAndCpf(issueId, cpf);
		return listVoteEntity;
	}

	public VoteEntity insert(VoteEntity voteEntity) {
		return voteRepository.insert(voteEntity);
	}
}
