package com.nolarity.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="rundowns")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rundown {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rundownId;
	
	private Date rundownStartTime;
	
	private Date rundownEndTime;
	
	private String rundownStatus;	// activated, deactivated, archived
	
	private String rundownTemplate;
	
	private String rundownTitle;
	
	private Date rundownCreatedDate;
	
	private String rundownCreatedBy;	// username
	
	private Date rundownModifiedDate;
	
	private String rundownModifiedBy;	// username
	
	@OneToMany
	@JoinTable(name="rundown_row_join",
	joinColumns = @JoinColumn(name = "rundown_id"),
	inverseJoinColumns = @JoinColumn(name = "row_id"))
	private Set<RundownRow> rundownRows;

	/**
	 * Constructor without id so it can be auto-generated.
	 * @param rundownStartTime
	 * @param rundownEndTime
	 * @param rundownStatus
	 * @param rundownTemplate
	 * @param rundownTitle
	 * @param rundownCreatedDate
	 * @param rundownCreatedBy
	 * @param rundownModifiedDate
	 * @param rundownModifiedBy
	 */
	public Rundown(Date rundownStartTime, Date rundownEndTime, String rundownStatus, String rundownTemplate,
			String rundownTitle, Date rundownCreatedDate, String rundownCreatedBy, Date rundownModifiedDate,
			String rundownModifiedBy) {
		super();
		this.rundownStartTime = rundownStartTime;
		this.rundownEndTime = rundownEndTime;
		this.rundownStatus = rundownStatus;
		this.rundownTemplate = rundownTemplate;
		this.rundownTitle = rundownTitle;
		this.rundownCreatedDate = rundownCreatedDate;
		this.rundownCreatedBy = rundownCreatedBy;
		this.rundownModifiedDate = rundownModifiedDate;
		this.rundownModifiedBy = rundownModifiedBy;
	}	
}
