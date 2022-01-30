package br.com.sicredi.votescount.domain.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sicredi.votescount.domain.entities.VoteEntity;

@Repository
public interface VoteRepository extends MongoRepository<VoteEntity, String> {

	List<VoteEntity> findByIssueIdAndCpf(String issueId, String cpf);
	
	@Query(value = "{issueId: ?0, vote:'S'}", count = true)
	public long countYes(String issueId);

	@Query(value = "{issueId: ?0, vote:'N'}", count = true)
	public long countNo(String issueId);

}
