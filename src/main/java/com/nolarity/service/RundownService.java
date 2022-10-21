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

import com.nolarity.exception.RundownNotFoundException;
import com.nolarity.model.Rundown;
import com.nolarity.model.RundownRow;
import com.nolarity.repository.RundownRepository;
import com.nolarity.repository.RundownRowRepository;

@Service
public class RundownService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RundownRepository rundownRepo;
	
	@Autowired
	private RundownRowRepository rowRepo;
	
	/**
	 * Gets all rundowns.
	 * @return
	 */
	@Transactional(readOnly = true)
	public Set<Rundown> getAllRundowns() {
		log.info("Get all rundowns.");
		return rundownRepo.findAll().stream().collect(Collectors.toSet());
	}
	
	/**
	 * ADD
	 * Adds a rundown with a row or rows.
	 * If no rows in request, it creates a new row in the rundown.
	 * @param rundown
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Rundown add(Rundown rundown) {
		System.out.println(rundown.getRundownId());
		Date currentDate = new Date(); 
		rundown.setRundownCreatedDate(currentDate);
		rundown.setRundownModifiedDate(currentDate);
		Set<RundownRow> rows = rundown.getRundownRows();
		if(rows.size() > 0) {
			for(RundownRow row : rows) {
				rowRepo.save(row);
			}
		} else {
			RundownRow newRow = new RundownRow("", 0, "story", rundown.getRundownCreatedDate(), rundown.getRundownCreatedBy(), rundown.getRundownModifiedDate(), rundown.getRundownModifiedBy(), null, "", "unapproved", "", "", "", "", "", "", "", "0:00", "", null, null, false);  
			rowRepo.save(newRow);
			rows.add(newRow);
		}
		return rundownRepo.save(rundown);
	}
	
	/**
	 * Gets rundown by id.
	 * @param rundownId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Rundown getRundownById(long rundownId) {
		Rundown rundown = checkIfRundownExists(rundownId);
		log.info("Found rundown with ID " + rundownId);
		return rundown;
	}
	
	/**
	 * MODIFY
	 * Modify rundown. Saves new version after setting created date, created by, and modified date.
	 * @param newVersion
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Rundown modifyRundown(Rundown newVersion) {
		long rundownId = newVersion.getRundownId();
		Rundown oldVersion = checkIfRundownExists(rundownId);
		Date currentDate = new Date();
		newVersion.setRundownCreatedDate(oldVersion.getRundownCreatedDate());
		newVersion.setRundownCreatedBy(oldVersion.getRundownCreatedBy());
		newVersion.setRundownCreatedDate(currentDate);
		return rundownRepo.save(newVersion); 
	}
	
	/**
	 * DELETE rundown
	 * @param rundownId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteRundown(long rundownId) {
		checkIfRundownExists(rundownId);
		log.info("Deleting rundown with ID " + rundownId);
		rundownRepo.deleteById(rundownId);
	}
	
	/**
	 * Checks if rundown exists. Throws exception if it doesn't. Returns story if it does.
	 * @param rundownId
	 * @return
	 */
	protected Rundown checkIfRundownExists(long rundownId) {
		Rundown rundown = rundownRepo.findByRundownId(rundownId);
		if(rundown == null) {
			log.warn("Rundown with ID " + rundownId + " is null.");
			throw new RundownNotFoundException("Rundown with ID " + rundownId + " not found.");
		}
		return rundown;
	}
}
