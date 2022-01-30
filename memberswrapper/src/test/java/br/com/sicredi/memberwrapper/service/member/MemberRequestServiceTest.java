package br.com.sicredi.memberwrapper.service.member;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Provider.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sicredi.memberwrapper.gateway.feing.MemberClient;
import br.com.sicredi.memberwrapper.gateway.json.MemberJson;

class MemberRequestServiceTest {

	@Autowired
	private MemberRequestService service;

	@Mock private MemberClient memberClient;

	@Nested
	@DisplayName("MemberRequestService")
	class Execute {

		@Test
	    @DisplayName("Should return Not Found when a parameter is empty")
	    void shouldReturnNotFoundWhenParameterIsEmpty() {
	      String emptyParameter = "";

	      when(memberClient.getAuthorization(emptyParameter)).thenReturn(new MemberJson());

	      MemberJson response = service.execute(emptyParameter);

	      assertEquals(response, new MemberJson());
//	      verify(repository, times(1)).save(any(Group.class));
	    }
	
	
	}

}
