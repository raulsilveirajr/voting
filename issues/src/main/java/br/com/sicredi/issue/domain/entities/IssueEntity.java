package br.com.sicredi.issue.domain.entities;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "issues")
public class IssueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	private String id;

	private String name;
	private String description;

	@CreatedDate
	private Instant createdAt;
	
	@LastModifiedDate
	private Instant updatedAt;

	private Instant startVotationAt;
	private Instant endVotationAt;
	
	@Transient
	private Integer yesVotes;
	
	@Transient
	private Integer noVotes;

	public IssueEntity(String id, String name, String description, Instant createdAt, Instant updatedAt,
			Instant startVotationAt, Instant endVotationAt) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.startVotationAt = startVotationAt;
		this.endVotationAt = endVotationAt;
	}

}
