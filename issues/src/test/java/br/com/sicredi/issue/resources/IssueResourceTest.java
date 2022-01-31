package br.com.sicredi.issue.resources;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.net.HttpHeaders;

import br.com.sicredi.issue.domain.dtos.IssueDTO;
import br.com.sicredi.issue.domain.entities.IssueEntity;
import br.com.sicredi.issue.domain.services.IssueService;
import br.com.sicredi.issue.domain.services.exceptions.ObjectNotFoundException;
import br.com.sicredi.issue.stubs.Stub;

//@TestPropertySource(locations="classpath:application.yml")
//@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
public class IssueResourceTest {

	@Mock
	private IssueService issueService;

	private IssueResource issueResource;
 	private MockHttpServletRequest mockRequest = new MockHttpServletRequest();


 	@BeforeEach
    public void setupEach(){
		issueResource = new IssueResource(issueService);

		mockRequest = new MockHttpServletRequest();
 	 	mockRequest.setContextPath("/issues");
 	 	ServletRequestAttributes attrs = new ServletRequestAttributes(mockRequest);
 	 	RequestContextHolder.setRequestAttributes(attrs);
    }
	
    @Nested
	@DisplayName("findAll")
	class findAllTest {
		@Test
		@DisplayName("Should return a list of issues")
		void shouldReturnAListOfIssues() {
			List<IssueEntity> listIssuesExpected = Stub.getListOfIssues();
			List<IssueDTO> listIssuesDTO = listIssuesExpected
					.stream()
					.map(issue -> new IssueDTO(issue))
					.collect(Collectors.toList());

			when(issueService.findAll()).thenReturn(listIssuesExpected);
	
			ResponseEntity<List<IssueDTO>> result = issueResource.findAll();

			assertEquals(result.getBody().size(), 3);
			assertEquals(result.getBody(), listIssuesDTO);
		}

		@Test
		@DisplayName("Should return a empty list of issues")
		void shouldReturnAEmptyListOfIssues() {
			List<IssueEntity> listIssuesExpected = new ArrayList<IssueEntity>();
			when(issueService.findAll()).thenReturn(listIssuesExpected);

			ResponseEntity<List<IssueDTO>> result = issueResource.findAll();

			assertNull(result.getBody());
		}
    }
	
    @Nested
	@DisplayName("findById")
	class findByIdTest {
		@Test
		@DisplayName("Should return a issue when issue found")
		void shouldReturnAIssueWhenIssueFound() {
			when(issueService.findById(Stub.ISSUE_ID)).thenReturn(Stub.getIssue());

			ResponseEntity<IssueDTO> result = issueResource.findById(Stub.ISSUE_ID);

			assertEquals(HttpStatus.OK, result.getStatusCode());
			assertEquals(result.getBody(), new IssueDTO(Stub.getIssue()));
			verify(issueService, times(1)).findById(Stub.ISSUE_ID);
		}

		@Test
		@DisplayName("Should return Not Found Http code when issue not found")
		void shouldReturnNotFoundHttpCodeWhenIssueNotFound() {
			when(issueService.findById(Stub.ISSUE_ID)).thenThrow(ObjectNotFoundException.class);

			ResponseEntity<IssueDTO> result = issueResource.findById(Stub.ISSUE_ID);
						
			assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
			assertNull(result.getBody());
			verify(issueService, times(1)).findById(Stub.ISSUE_ID);
		}
    }
	
    @Nested
	@DisplayName("insert")
	class insertTest {
		@Test
		@DisplayName("Should return Created Http code when save")
		void shouldReturnCreatedHttpCodeWhenSave() {
			IssueEntity issueEntity = Stub.getIssue();
			IssueDTO issueDTO = new IssueDTO(issueEntity);
			
			when(issueService.insert(issueEntity)).thenReturn(issueEntity);
			when(issueService.fromDTO(issueDTO)).thenReturn(issueEntity);
			
			ResponseEntity<Void> result = issueResource.insert(issueDTO);

			assertEquals(HttpStatus.CREATED, result.getStatusCode());
			assertTrue(result.getHeaders().get(HttpHeaders.LOCATION).toString().contains(issueEntity.getId()));
			verify(issueService, times(1)).insert(issueEntity);
			verify(issueService, times(1)).fromDTO(issueDTO);
		}
    }
	
    @Nested
	@DisplayName("update")
	class updateTest {
		@Test
		@DisplayName("Should return No Content Http code when save")
		void shouldReturnCreatedHttpCodeWhenSave() {
			IssueEntity issueEntity = Stub.getIssue();
			IssueDTO issueDTO = new IssueDTO(issueEntity);

			when(issueService.findById(issueEntity.getId())).thenReturn(Stub.getIssue());
			when(issueService.update(issueEntity)).thenReturn(issueEntity);
			when(issueService.fromDTO(issueDTO)).thenReturn(issueEntity);

			ResponseEntity<Void> result = issueResource.update(issueEntity.getId(), issueDTO);

			assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
			assertNull(result.getBody());
			verify(issueService, times(1)).findById(issueEntity.getId());
			verify(issueService, times(1)).update(issueEntity);
			verify(issueService, times(1)).fromDTO(issueDTO);
		}

		@Test
		@DisplayName("Should return a Not Found Http code when issue not found")
		void shouldReturnANotFoundHttpCodeWhenIssueNotFound() {
			IssueEntity issueEntity = Stub.getIssueOptional().get();
			IssueDTO issueDTO = new IssueDTO(issueEntity);

			when(issueService.findById(Stub.ISSUE_ID)).thenThrow(ObjectNotFoundException.class);

			ResponseEntity<Void> result = issueResource.update(Stub.ISSUE_ID, issueDTO);
						
			assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
			assertNull(result.getBody());
			verify(issueService, times(1)).findById(Stub.ISSUE_ID);
		}
    }

    @Nested
	@DisplayName("startVotation")
	class startVotationTest {
		@Test
		@DisplayName("Should return No Content Http code when save")
		void shouldReturnCreatedHttpCodeWhenSave() {
			IssueEntity issueEntity = Stub.getIssue();
			Long minutesToVote = Long.parseLong(String.valueOf(90));

			when(issueService.startVotation(issueEntity.getId(), minutesToVote)).thenReturn(issueEntity);

			ResponseEntity<Void> result = issueResource.startVotation(issueEntity.getId(), minutesToVote);

			assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
			assertNull(result.getBody());
			verify(issueService, times(1)).startVotation(issueEntity.getId(), minutesToVote);
		}

		@Test
		@DisplayName("Should return Not Found Http code when issue not found")
		void shouldReturnNotFoundHttpCodeWhenIssueNotFound() {
			when(issueService.findById(Stub.ISSUE_ID)).thenThrow(ObjectNotFoundException.class);

			ResponseEntity<IssueDTO> result = issueResource.findById(Stub.ISSUE_ID);
						
			assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
			assertNull(result.getBody());
			verify(issueService, times(1)).findById(Stub.ISSUE_ID);
		}
    }

    @Nested
	@DisplayName("getResults")
	class getResultsTest {
		@Test
		@DisplayName("Should return a issue when issue found")
		void shouldReturnAIssueWhenIssueFound() {
			IssueEntity issueEntity = Stub.getIssue();

			when(issueService.getResults(issueEntity.getId())).thenReturn(issueEntity);

			ResponseEntity<IssueDTO> result = issueResource.getResults(issueEntity.getId());

			assertEquals(HttpStatus.OK, result.getStatusCode());
			assertEquals(result.getBody(), new IssueDTO(issueEntity));
			verify(issueService, times(1)).getResults(issueEntity.getId());
		}

		@Test
		@DisplayName("Should return a Not Found Http code when issue not found")
		void shouldReturnANotFoundHttpCodeWhenIssueNotFound() {
			ResponseEntity<IssueDTO> result = issueResource.getResults(Stub.ISSUE_ID);

			assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
			assertNull(result.getBody());
		}
    }
}
