package br.com.sicredi.issue.domain.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.issue.domain.entities.IssueEntity;

@Repository
public interface IssueRepository extends MongoRepository<IssueEntity, String>  {
}
