package com.nolarity.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="stories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long storyId;
	
	private String storyTitle;
	
	@Length(max = 10000)
	private String storyScript;
	
	private String storyWriter;	// username
	
	private Date storyCreatedDate;
	
	private String storyCreatedBy;	// username
	
	private Date storyModifiedDate;
	
	private String storyModifiedBy;	// username

	/**
	 * Constructor without id so it can be auto-generated.
	 * @param storyTitle
	 * @param storyScript
	 * @param storyWriter
	 * @param storyCreatedDate
	 * @param storyCreatedBy
	 * @param storyModifiedDate
	 * @param storyModifiedBy
	 */
	public Story(String storyTitle, String storyScript, String storyWriter, Date storyCreatedDate,
			String storyCreatedBy, Date storyModifiedDate, String storyModifiedBy) {
		super();
		this.storyTitle = storyTitle;
		this.storyScript = storyScript;
		this.storyWriter = storyWriter;
		this.storyCreatedDate = storyCreatedDate;
		this.storyCreatedBy = storyCreatedBy;
		this.storyModifiedDate = storyModifiedDate;
		this.storyModifiedBy = storyModifiedBy;
	}
}
