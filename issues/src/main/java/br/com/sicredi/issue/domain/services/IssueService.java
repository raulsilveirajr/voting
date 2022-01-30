package br.com.sicredi.issue.domain.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.sicredi.issue.domain.dtos.IssueDTO;
import br.com.sicredi.issue.domain.entities.IssueEntity;
import br.com.sicredi.issue.domain.feign.VotescountClient;
import br.com.sicredi.issue.domain.jsons.VotescountJson;
import br.com.sicredi.issue.domain.repositories.IssueRepository;
import br.com.sicredi.issue.domain.services.exceptions.ObjectNotFoundException;
import feign.Feign;
import feign.gson.GsonDecoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

@Service
public class IssueService {
	
	@Autowired
	private IssueRepository	issueRepository;
	
	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private String redisPort;

	@Value("${spring.redis.password}")
	private String redisPassword;

	public List<IssueEntity> findAll() {
		return issueRepository.findAll();
	}

	public IssueEntity findById(String id) {
		return issueRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Issue not found"));
	}
	
	public IssueEntity insert(IssueEntity issueEntity) {
		return issueRepository.insert(issueEntity);
	}
	
	public IssueEntity update(IssueEntity issueEntity) {
		IssueEntity newIssueEntity = issueRepository.findById(issueEntity.getId())
				.orElseThrow(() -> new ObjectNotFoundException("Issue not found"));

		newIssueEntity.setName(issueEntity.getName());
		newIssueEntity.setDescription(issueEntity.getDescription());
		return issueRepository.save(newIssueEntity);
	}
	
	public IssueEntity startVotation(String id, Long minutesToVote) {
		IssueEntity newIssueEntity = issueRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Issue not found"));

		Instant start = Instant.now();
	    Instant end = start.plus(minutesToVote, ChronoUnit.MINUTES);
		newIssueEntity.setStartVotationAt(start);
		newIssueEntity.setEndVotationAt(end);
		
		cleanRedis();

		return issueRepository.save(newIssueEntity);
	}
	
	public IssueEntity getResults(String id) {
		IssueEntity issueEntity = issueRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Issue not found"));

 		try {
 			VotescountClient votescountClient = Feign.builder()
					.decoder(new GsonDecoder())
					.target(VotescountClient.class, "http://localhost:8085");

			VotescountJson VotescountJson = votescountClient.getResults(id);
			issueEntity.setYesVotes(VotescountJson.getVotesYes().intValue());
			issueEntity.setNoVotes(VotescountJson.getVotesNo().intValue());
  		} catch (Exception e) {
			// e.printStackTrace();
		}		

		return issueEntity;
	}

	// REALOCAR
	public void cleanRedis() {
		JedisShardInfo shardInfo = new JedisShardInfo(redisHost, redisPort);
		shardInfo.setPassword(redisPassword);
		Jedis jedis = new Jedis(shardInfo);
		jedis.connect();		
		jedis.flushAll();
	}

	public IssueEntity fromDTO( IssueDTO issueDTO ) {
		return new IssueEntity(
			issueDTO.getId(),
			issueDTO.getName(),
			issueDTO.getDescription(),
			issueDTO.getCreatedAt(),
			issueDTO.getUpdatedAt(),
			issueDTO.getStartVotationAt(),
			issueDTO.getEndVotationAt(),
			0,
			0);
	}
 }
