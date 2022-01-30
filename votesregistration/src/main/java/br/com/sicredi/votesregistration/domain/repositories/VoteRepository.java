package br.com.sicredi.votesregistration.domain.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.votesregistration.domain.entities.VoteEntity;

@Repository
public interface VoteRepository extends MongoRepository<VoteEntity, String> {

	List<VoteEntity> findByIssueIdAndCpf(String issueId, String cpf);
}
