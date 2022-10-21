package com.nolarity.service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nolarity.exception.RowNotFoundException;
import com.nolarity.model.RundownRow;
import com.nolarity.repository.RundownRowRepository;

@Service
public class RundownRowService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RundownRowRepository rowRepo; 
	
	/**
	 * Gets all rows.
	 * @return
	 */
	@Transactional(readOnly = true)
	public Set<RundownRow> getAllRows() {
		log.info("Get all rundown rows.");
		return rowRepo.findAll().stream().collect(Collectors.toSet());
	}
	
	/**
	 * Adds row.
	 * @param row
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public RundownRow add(RundownRow row) {
		Date currentDate = new Date(); 
		row.setRowCreatedDate(currentDate);
		row.setRowModifiedDate(currentDate);
		return rowRepo.save(row);
	}

	/**
	 * Gets row by id.
	 * @param rowId
	 * @return
	 */
	@Transactional(readOnly = true)
	public RundownRow getRowById(long rowId) {
		RundownRow row = checkIfRowExists(rowId);
		log.info("Found row with ID " + rowId);
		return row;
	}
	
	/**
	 * Modify row. Saves new version after setting created date, created by, and modified date.
	 * @param newVersion
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)	// default
	public RundownRow modifyRow(RundownRow newVersion) {
		long rowId = newVersion.getRowId();
		RundownRow oldVersion = checkIfRowExists(rowId);
		Date currentDate = new Date();
		newVersion.setRowCreatedDate(oldVersion.getRowCreatedDate());
		newVersion.setRowCreatedBy(oldVersion.getRowCreatedBy());
		newVersion.setRowModifiedDate(currentDate);
		return rowRepo.save(newVersion);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteRow(long rowId) {
		checkIfRowExists(rowId);
		log.info("Deleting row with ID " + rowId);
		rowRepo.deleteById(rowId);
	}
	
	/**
	 * Checks if row exists. Throws exception if it doesn't. Return rundown row if it does.
	 * @param rowId
	 */
	public RundownRow checkIfRowExists(long rowId) {
		RundownRow row = rowRepo.findByRowId(rowId); 
		if( row == null) {
			log.warn("Row with ID " + rowId + " is null.");
			throw new RowNotFoundException("Row with ID " + rowId + " not found.");
		}
		return row;
	}
}
