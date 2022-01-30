package br.com.sicredi.issue.domain.feign;

import org.springframework.cloud.openfeign.FeignClient;

import br.com.sicredi.issue.domain.jsons.VotescountJson;
import feign.Param;
import feign.RequestLine;

@FeignClient(name = "votescount")
public interface VotescountClient {
    @RequestLine("GET /votescount/{id}")
    VotescountJson getResults(@Param("id") String id);
}



	