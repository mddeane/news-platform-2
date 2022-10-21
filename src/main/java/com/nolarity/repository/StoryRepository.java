package com.nolarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nolarity.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long>{

	Story findByStoryId(long storyId);
	
}
