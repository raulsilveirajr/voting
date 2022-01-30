package br.com.sicredi.votesregistration.domain.services.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.sicredi.votesregistration.domain.dtos.VoteDTO;
import br.com.sicredi.votesregistration.domain.jsons.VoteJson;
import br.com.sicredi.votesregistration.domain.services.RegisterVoteService;

@Component
public class QueueConsumer {
	
	@Autowired
	private RegisterVoteService registerVoteService;
	
	@RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String jsonVoteDTO) {
        System.out.println("Registering Vote (RabbitListener) " + jsonVoteDTO.toString());
        Gson gson = new Gson();
        VoteJson votejson = gson.fromJson(jsonVoteDTO, VoteJson.class);
        VoteDTO voteDTO = new VoteDTO(votejson); 
        registerVoteService.execute(voteDTO);
    }
}
