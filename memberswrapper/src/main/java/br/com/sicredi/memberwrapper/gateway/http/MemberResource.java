package br.com.sicredi.memberwrapper.gateway.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.memberwrapper.gateway.json.MemberJson;
import br.com.sicredi.memberwrapper.service.member.MemberRequestService;

@RestController
@RequestMapping("/members")
public class MemberResource {

	@Autowired
	private MemberRequestService memberRequestService;
	
	@GetMapping("/valid")
	public <T> ResponseEntity<MemberJson> validMember(@RequestParam String cpf) {
		System.out.println("========================");
		System.out.println("Resource");
		MemberJson member = memberRequestService.execute(cpf);
		System.out.println("Resource");
		System.out.println(member.toString());

		if (member.equals(new MemberJson())) {
            return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<MemberJson>(
				member, HttpStatus.OK);
	}
}
