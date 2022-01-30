package br.com.sicredi.votes.domain.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votes.domain.dtos.VoteDTO;
import br.com.sicredi.votes.domain.services.queue.QueueSender;

@Service
public class VoteService {

	@Autowired
	private QueueSender queueSender;

	public void registerVote(VoteDTO voteDTO) {
		queueSender.send(voteDTO);			
	}
}
