package br.com.sicredi.memberwrapper.service.member;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sicredi.memberwrapper.gateway.feing.MemberClient;
import br.com.sicredi.memberwrapper.gateway.json.MemberJson;
import feign.Feign;
import feign.gson.GsonDecoder;

@Service
public class MemberRequestService {
	public MemberJson execute(String cpf) {
		MemberClient memberClient = Feign.builder()
			.decoder(new GsonDecoder())
			.target(MemberClient.class, "https://user-info.herokuapp.com");

		try {
			System.out.println("Service");
			MemberJson member = memberClient.getAuthorization(cpf);
			System.out.println("Buscando.:" + cpf);
			System.out.println("Result.:" + member.toString());
			return member;
		} catch (Exception e) {
			System.out.println("Exception.:" + e.getMessage());
			// TODO: handle exception
		}

		return new MemberJson();
	}
}
