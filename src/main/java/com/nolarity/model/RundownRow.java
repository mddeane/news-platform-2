package com.nolarity.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="rundown_rows")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RundownRow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rowId;
	
	private String rowPageNumber;
	
	private long rowStoryId;
	
	private String rowType;
	
	private Date rowCreatedDate;
	
	private String rowCreatedBy;	// username
	
	private Date rowModifiedDate;
	
	private String rowModifiedBy;	// username
	
	private Date rowApprovedDate;
	
	private String rowApprovedBy;	// username
	
	private String rowApprovalStatus;
	
	private String rowSegment;
	
	private String rowReader;
	
	private String rowCamera;
	
	private String rowGraphic;
	
	private String rowSource;
	
	private String rowNotes;
	
	private String rowVideo;
	
	private String rowEstDuration;
	
	private String rowActDuration;
	
	private Date rowFrontTime;
	
	private Date rowBackTime;
	
	private boolean rowIsFloated;
	
	/**
	 * Constructor without id so it can be auto-generated.
	 * @param rowPageNumber
	 * @param rowStoryId
	 * @param rowType
	 * @param rowCreatedDate
	 * @param rowCreatedBy
	 * @param rowModifiedDate
	 * @param rowModifiedBy
	 * @param rowApprovedDate
	 * @param rowApprovedBy
	 * @param rowApprovalStatus
	 * @param rowSegment
	 * @param rowReader
	 * @param rowCamera
	 * @param rowGraphic
	 * @param rowSource
	 * @param rowNotes
	 * @param rowVideo
	 * @param rowEstDuration
	 * @param rowActDuration
	 * @param rowFrontTime
	 * @param rowBackTime
	 * @param rowIsFloated
	 */
	public RundownRow(String rowPageNumber, long rowStoryId, String rowType, Date rowCreatedDate, String rowCreatedBy,
			Date rowModifiedDate, String rowModifiedBy, Date rowApprovedDate, String rowApprovedBy,
			String rowApprovalStatus, String rowSegment, String rowReader, String rowCamera, String rowGraphic,
			String rowSource, String rowNotes, String rowVideo, String rowEstDuration, String rowActDuration,
			Date rowFrontTime, Date rowBackTime, boolean rowIsFloated) {
		super();
		this.rowPageNumber = rowPageNumber;
		this.rowStoryId = rowStoryId;
		this.rowType = rowType;
		this.rowCreatedDate = rowCreatedDate;
		this.rowCreatedBy = rowCreatedBy;
		this.rowModifiedDate = rowModifiedDate;
		this.rowModifiedBy = rowModifiedBy;
		this.rowApprovedDate = rowApprovedDate;
		this.rowApprovedBy = rowApprovedBy;
		this.rowApprovalStatus = rowApprovalStatus;
		this.rowSegment = rowSegment;
		this.rowReader = rowReader;
		this.rowCamera = rowCamera;
		this.rowGraphic = rowGraphic;
		this.rowSource = rowSource;
		this.rowNotes = rowNotes;
		this.rowVideo = rowVideo;
		this.rowEstDuration = rowEstDuration;
		this.rowActDuration = rowActDuration;
		this.rowFrontTime = rowFrontTime;
		this.rowBackTime = rowBackTime;
		this.rowIsFloated = rowIsFloated;
	}
}
