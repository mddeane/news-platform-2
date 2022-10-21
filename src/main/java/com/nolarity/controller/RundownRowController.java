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

import com.nolarity.model.RundownRow;
import com.nolarity.service.RundownRowService;

@RestController
@RequestMapping("rows")
@CrossOrigin(origins="*", allowedHeaders="*")
public class RundownRowController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RundownRowService rowServ;

	@GetMapping
	public ResponseEntity<Set<RundownRow>> getAllRows() {
		return ResponseEntity.ok(rowServ.getAllRows());
	}

	@PostMapping("/add")
	public ResponseEntity<RundownRow> addRow(@Valid @RequestBody RundownRow row) {
		ResponseEntity<RundownRow> addedRow = ResponseEntity.ok(rowServ.add(row));
		log.info("Row with ID " + row.getRowId() + " added by " + row.getRowCreatedBy() + " at " + row.getRowCreatedDate());
		return addedRow;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RundownRow> getRowById(@PathVariable("id") long rowId) {
		return ResponseEntity.ok(rowServ.getRowById(rowId));
	}
	
	@PutMapping("/modify")
	public ResponseEntity<RundownRow> modifyRow(@Valid @RequestBody RundownRow row) {
		return ResponseEntity.ok(rowServ.modifyRow(row));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteRow(@PathVariable("id") long rowId) {
		rowServ.deleteRow(rowId);
	}
}
