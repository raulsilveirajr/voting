package br.com.sicredi.issue.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.sicredi.issue.domain.entities.IssueEntity;
import br.com.sicredi.issue.domain.repositories.IssueRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private IssueRepository issueRepository;
	
	@Override
	public void run(String... args) throws Exception {
		/*
		Instant now = Instant.now();
		IssueEntity a = new IssueEntity(null, "PAUTA 1", "1a PAUTA", now, now, null, null );
		IssueEntity b = new IssueEntity(null, "PAUTA 2", "2a PAUTA", now, now, null, null );
		IssueEntity c = new IssueEntity(null, "PAUTA 3", "3a PAUTA", now, now, null, null );

		issueRepository.deleteAll();
		issueRepository.saveAll(Arrays.asList(a, b, c));
		*/
	}

}
