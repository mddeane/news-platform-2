package com.nolarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nolarity.model.Rundown;

@Repository
public interface RundownRepository extends JpaRepository<Rundown, Long>{

	public Rundown findByRundownId(long rundownId);
	
}
