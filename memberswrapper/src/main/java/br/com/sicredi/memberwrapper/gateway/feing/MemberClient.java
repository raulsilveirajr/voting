package br.com.sicredi.memberwrapper.gateway.feing;

import org.springframework.cloud.openfeign.FeignClient;

import br.com.sicredi.memberwrapper.gateway.json.MemberJson;
import feign.Param;
import feign.RequestLine;

@FeignClient(name = "MemberClient")
public interface MemberClient {
    @RequestLine("GET /users/{cpf}")
    MemberJson getAuthorization(@Param("cpf") String cpf);
}
