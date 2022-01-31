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

import org.junit.Before;
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
import br.com.sicredi.issue.domain.repositories.IssueRepository;
import br.com.sicredi.issue.domain.services.IssueService;
import br.com.sicredi.issue.stubs.Stub;

@ExtendWith(MockitoExtension.class)
public class IssueResourceTest {

	@Mock
	private IssueRepository issueRepository;

	private IssueService issueService;
 	private IssueResource issueResource;

 	private MockHttpServletRequest mockRequest = new MockHttpServletRequest();

 	@Before
    public void setup(){
 		mockRequest = new MockHttpServletRequest();
 	 	mockRequest.setContextPath("/issues");
 	 	ServletRequestAttributes attrs = new ServletRequestAttributes(mockRequest);
 	 	RequestContextHolder.setRequestAttributes(attrs);
    }

 	@BeforeEach
    public void setupEach(){
 		issueService = new IssueService(issueRepository);
		issueResource = new IssueResource(issueService);
    }
	
    @Nested
	@DisplayName("findAll")
	class findAllTest {
		@Test
		@DisplayName("Should return a empty list of issues")
		void shouldReturnAEmptyListOfIssues() {
			List<IssueEntity> listIssuesExpected = new ArrayList<IssueEntity>();
			when(issueRepository.findAll()).thenReturn(listIssuesExpected);

			ResponseEntity<List<IssueDTO>> result = issueResource.findAll();

			assertNull(result.getBody());
		}

		@Test
		@DisplayName("Should return a list of issues")
		void shouldReturnAListOfIssues() {
			List<IssueEntity> listIssuesExpected = Stub.getListOfIssues();
			List<IssueDTO> listIssuesDTO = listIssuesExpected
					.stream()
					.map(issue -> new IssueDTO(issue))
					.collect(Collectors.toList());

			when(issueRepository.findAll()).thenReturn(listIssuesExpected);
	
			ResponseEntity<List<IssueDTO>> result = issueResource.findAll();

			assertEquals(result.getBody().size(), 3);
			assertEquals(result.getBody(), listIssuesDTO);
		}
    }
	
    @Nested
	@DisplayName("findById")
	class findByIdTest {
		@Test
		@DisplayName("Should return a not found Http code when issue not found")
		void shouldReturnANotFoundHttpCodeWhenIssueNotFound() {
			when(issueRepository.findById(Stub.ISSUE_ID)).thenReturn(null);

			ResponseEntity<IssueDTO> result = issueResource.findById(Stub.ISSUE_ID);
						
			assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
			assertNull(result.getBody());
			verify(issueRepository, times(1)).findById(Stub.ISSUE_ID);
		}

		@Test
		@DisplayName("Should return a issue when issue found")
		void shouldReturnAIssueWhenIssueFound() {
			when(issueRepository.findById(Stub.ISSUE_ID)).thenReturn(Stub.getIssueOptional());

			ResponseEntity<IssueDTO> result = issueResource.findById(Stub.ISSUE_ID);

			assertEquals(HttpStatus.OK, result.getStatusCode());
			assertEquals(result.getBody(), new IssueDTO(Stub.getIssueOptional().get()));
			
			verify(issueRepository, times(1)).findById(Stub.ISSUE_ID);

		}
    }
	
    @Nested
	@DisplayName("insert")
	class insertTest {
		@Test
		@DisplayName("Should return Created Http code when save")
		void shouldReturnCreatedHttpCodeWhenSave() {
			IssueEntity issueEntity = Stub.getIssue();

			when(issueRepository.insert(issueEntity)).thenReturn(issueEntity);

	 	 	mockRequest.setContextPath("/issues");
	 	 	ServletRequestAttributes attrs = new ServletRequestAttributes(mockRequest);
	 	 	RequestContextHolder.setRequestAttributes(attrs);
			
			ResponseEntity<Void> result = issueResource.insert(new IssueDTO(issueEntity));

			assertEquals(HttpStatus.CREATED, result.getStatusCode());
			assertTrue(result.getHeaders().get(HttpHeaders.LOCATION).toString().contains(issueEntity.getId()));
			verify(issueRepository, times(1)).insert(issueEntity);
		}
    }
	
    @Nested
	@DisplayName("update")
	class updateTest {
		@Test
		@DisplayName("Should return No Content Http code when save")
		void shouldReturnCreatedHttpCodeWhenSave() {
			IssueEntity issueEntity = Stub.getIssueOptional().get();

			when(issueRepository.findById(issueEntity.getId())).thenReturn(Stub.getIssueOptional());
			when(issueRepository.save(issueEntity)).thenReturn(issueEntity);

			ResponseEntity<Void> result = issueResource.update(issueEntity.getId(), new IssueDTO(issueEntity));

			assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
			assertNull(result.getBody());
			verify(issueRepository, times(1)).findById(issueEntity.getId());
			verify(issueRepository, times(1)).save(issueEntity);
		}

		@Test
		@DisplayName("Should return a Not Found Http code when issue not found")
		void shouldReturnANotFoundHttpCodeWhenIssueNotFound() {
			IssueEntity issueEntity = Stub.getIssueOptional().get();

			when(issueRepository.findById(issueEntity.getId())).thenReturn(null);

			ResponseEntity<Void> result = issueResource.update(issueEntity.getId(), new IssueDTO(issueEntity));
						
			assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
			assertNull(result.getBody());
			verify(issueRepository, times(1)).findById(Stub.ISSUE_ID);
		}
    }
	
    @Nested
	@DisplayName("startVotation")
	class startVotationTest {
		@Test
		@DisplayName("Should return No Content Http code when save")
		void shouldReturnCreatedHttpCodeWhenSave() {
			IssueEntity issueEntity = Stub.getIssueOptional().get();

			when(issueRepository.findById(issueEntity.getId())).thenReturn(Stub.getIssueOptional());
			when(issueRepository.save(issueEntity)).thenReturn(issueEntity);

			ResponseEntity<Void> result = issueResource.update(issueEntity.getId(), new IssueDTO(issueEntity));

			assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
			assertNull(result.getBody());
			verify(issueRepository, times(1)).findById(issueEntity.getId());
			verify(issueRepository, times(1)).save(issueEntity);
		}

		@Test
		@DisplayName("Should return a Not Found Http code when issue not found")
		void shouldReturnANotFoundHttpCodeWhenIssueNotFound() {
			IssueEntity issueEntity = Stub.getIssueOptional().get();

			when(issueRepository.findById(issueEntity.getId())).thenReturn(null);

			ResponseEntity<Void> result = issueResource.update(issueEntity.getId(), new IssueDTO(issueEntity));
						
			assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
			assertNull(result.getBody());
			verify(issueRepository, times(1)).findById(Stub.ISSUE_ID);
		}
    }
}
