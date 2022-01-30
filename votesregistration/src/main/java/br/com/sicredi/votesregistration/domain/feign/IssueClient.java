package br.com.sicredi.votesregistration.domain.feign;

import org.springframework.cloud.openfeign.FeignClient;

import br.com.sicredi.votesregistration.domain.jsons.IssueJson;
import feign.Param;
import feign.RequestLine;

@FeignClient(name = "issues")
public interface IssueClient {
    @RequestLine("GET /issues/{id}")
    IssueJson getIssue(@Param("id") String id);
}
