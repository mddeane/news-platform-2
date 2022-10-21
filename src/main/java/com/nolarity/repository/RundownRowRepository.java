package com.nolarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nolarity.model.RundownRow;

@Repository
public interface RundownRowRepository extends JpaRepository<RundownRow, Long>{
	
	RundownRow findByRowId(long rowId);

}
