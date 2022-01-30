package br.com.sicredi.votes.domain.services.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sicredi.votes.domain.dtos.VoteDTO;

@Component
public class QueueSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(VoteDTO voteDTO) {
    	// Creating Object of ObjectMapper define in Jakson Api
    	ObjectMapper mapper = new ObjectMapper();

    	try {
			String jsonVoteDTO = mapper.writeValueAsString(voteDTO);
			rabbitTemplate.convertAndSend(this.queue.getName(), jsonVoteDTO);
			// System.out.println("Pub in queue [" + this.queue.getName() + "] -> "+jsonVoteDTO);
		} catch (JsonProcessingException e) {
			// TODO: Tratar essa exception
			e.printStackTrace();
		}
    }
}
