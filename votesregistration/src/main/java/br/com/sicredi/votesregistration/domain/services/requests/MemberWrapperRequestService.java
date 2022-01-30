package br.com.sicredi.votesregistration.domain.services.requests;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.sicredi.votesregistration.domain.dtos.IssueDTO;
import br.com.sicredi.votesregistration.domain.dtos.MemberWrapperDTO;
import br.com.sicredi.votesregistration.domain.feign.IssueClient;
import br.com.sicredi.votesregistration.domain.feign.MemberWrapperClient;
import br.com.sicredi.votesregistration.domain.jsons.IssueJson;
import br.com.sicredi.votesregistration.domain.jsons.MemberWrapperJson;
import feign.Feign;
import feign.gson.GsonDecoder;

@Service
public class MemberWrapperRequestService {

    @Cacheable(value="member_wrapper")
    public MemberWrapperDTO execute(String cpf) {
 		try {
			MemberWrapperClient memberWrapperClient = Feign.builder()
					.decoder(new GsonDecoder())
					.target(MemberWrapperClient.class, "http://localhost:8088");

			MemberWrapperJson memberWrapperJson = memberWrapperClient.getMemberStatus(cpf);
			// System.out.println("MemberWrapperRequestService execute >> " + memberWrapperJson.toString());
			return new MemberWrapperDTO(memberWrapperJson);
		} catch (Exception e) {
			// System.out.println("*** MemberWrapperRequestService Exception >> " + e.getMessage());
			// e.printStackTrace();
			return new MemberWrapperDTO();
		}
	}
}
