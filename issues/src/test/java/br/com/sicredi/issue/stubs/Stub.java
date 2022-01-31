package br.com.sicredi.issue.stubs;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.sicredi.issue.common.parsers.InstantParser;
import br.com.sicredi.issue.domain.entities.IssueEntity;

public class Stub {
	public static final String ISSUE_ID = "61f6d71e5fc5f26c5ac369be";

	public static IssueEntity getIssue() {
		Instant now = InstantParser.toInstant("2022-01-30T18:21:18.765Z");
		IssueEntity issueEntity = new IssueEntity( Stub.ISSUE_ID , "TESTE 1", "TESTE 1 - Description", now, now, null, null);

		return issueEntity;
    }

	public static Optional<IssueEntity> getIssueOptional() {
		return Optional.of(Stub.getIssue());
    }

	public static List<IssueEntity> getListOfIssues() {
		Instant now = InstantParser.toInstant("2022-01-30T18:21:18.765Z");
		IssueEntity issueEntityA = Stub.getIssue();
		IssueEntity issueEntityB = new IssueEntity("2", "TESTE 2", "TESTE 2 - Description", now, now, null, null);
		IssueEntity issueEntityC = new IssueEntity("3", "TESTE 3", "TESTE 3 - Description", now, now, null, null);
		List<IssueEntity> listIssuesExpected = new ArrayList<IssueEntity>();
		listIssuesExpected.add(issueEntityA);
		listIssuesExpected.add(issueEntityB);
		listIssuesExpected.add(issueEntityC);

		return listIssuesExpected;
    }
}
