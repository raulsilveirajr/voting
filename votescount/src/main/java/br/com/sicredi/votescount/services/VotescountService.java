package br.com.sicredi.votescount.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votescount.domain.jsons.VotescountJson;
import br.com.sicredi.votescount.domain.repositories.VoteRepository;

@Service
public class VotescountService {
	@Autowired
	private VoteRepository voteRepository;
	
	public VotescountJson countVotes(String id) {
		return new VotescountJson(id,
				voteRepository.countYes(id),
				voteRepository.countNo(id));		
	}
}
