package br.com.sicredi.votesregistration.domain.feign;

import org.springframework.cloud.openfeign.FeignClient;

import br.com.sicredi.votesregistration.domain.jsons.MemberWrapperJson;
import feign.Param;
import feign.RequestLine;

@FeignClient(name = "memberswrapper")
public interface MemberWrapperClient {
    @RequestLine("GET /members/valid?cpf={cpf}")
    MemberWrapperJson getMemberStatus(@Param("cpf") String cpf);
}
