package com.nolarity.controller;

import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nolarity.model.Rundown;
import com.nolarity.service.RundownService;

@RestController
@RequestMapping("rundowns")
@CrossOrigin(origins="*", allowedHeaders="*")
public class RundownController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RundownService rundownServ;
	
	@GetMapping
	public ResponseEntity<Set<Rundown>> getAllRundowns() {
		return ResponseEntity.ok(rundownServ.getAllRundowns()); 
	}
	
	@PostMapping("/add")
	public ResponseEntity<Rundown> addRundown(@Valid @RequestBody Rundown rundown) {
		ResponseEntity<Rundown> addedRundown = ResponseEntity.ok(rundownServ.add(rundown));
		log.info("Rundown ID " + addedRundown.getBody().getRundownId() + " added at " + addedRundown.getBody().getRundownCreatedDate() + " by " + addedRundown.getBody().getRundownCreatedBy());
		return addedRundown;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Rundown> getRundownById(@PathVariable("id") long rundownId) {
		Rundown rundown = rundownServ.getRundownById(rundownId);
		return ResponseEntity.ok(rundown);
	}
	
	@PutMapping("/modify")
	public ResponseEntity<Rundown> modifyRundown(@Valid @RequestBody Rundown rundown) {
		return ResponseEntity.ok(rundownServ.modifyRundown(rundown));
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteRundown(@PathVariable("id") long rundownId) {
		rundownServ.deleteRundown(rundownId);
	}
}
