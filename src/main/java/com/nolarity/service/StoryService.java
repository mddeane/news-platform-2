package com.nolarity.service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nolarity.exception.StoryNotFoundException;
import com.nolarity.model.Story;
import com.nolarity.repository.StoryRepository;

@Service
public class StoryService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StoryRepository storyRepo;
	
	/**
	 * Gets all stories.
	 * @return
	 */
	@Transactional(readOnly = true)
	public Set<Story> getAllStories() {
		log.info("Get all stories.");
		return storyRepo.findAll().stream().collect(Collectors.toSet());
	}
	
	/**
	 * ADD
	 * Adds a story.
	 * @param story
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Story add(Story story) {
		Date currentDate = new Date(); 
		story.setStoryCreatedDate(currentDate);
		story.setStoryModifiedDate(currentDate);
		return storyRepo.save(story);
	}
	
	/**
	 * Gets a story by id.
	 * @param storyId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Story getStoryById(long storyId) {
		Story story = checkIfStoryExists(storyId);
		log.info("Found row with ID " + storyId);
		return story;
	}
	
	/**
	 * MODIFY
	 * Modify story. Saves new version after setting created date, created by, and modified date.
	 * @param newVersion
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)	// default
	public Story modifyStory(Story newVersion) {
		long storyId = newVersion.getStoryId();
		Story oldVersion = checkIfStoryExists(storyId);
		Date currentDate = new Date();
		newVersion.setStoryCreatedDate(oldVersion.getStoryCreatedDate());
		newVersion.setStoryCreatedBy(oldVersion.getStoryCreatedBy());
		newVersion.setStoryModifiedDate(currentDate);
		return storyRepo.save(newVersion);
	}

	/**
	 * DELETE story
	 * @param storyId
	 */
	@Transactional(propagation=Propagation.REQUIRED)	// default
	public void deleteStory(long storyId) {
		checkIfStoryExists(storyId);
		log.info("Deleting story with ID " + storyId);
		storyRepo.deleteById(storyId);
	}
	
	/**
	 * Checks if story exists. Throws exception if it doesn't. Returns story if it does.
	 * @param storyId
	 */
	protected Story checkIfStoryExists(long storyId) {
		Story story = storyRepo.findByStoryId(storyId);
		if(story == null) {
			log.warn("Story with ID " + storyId + " is null.");
			throw new StoryNotFoundException("Story with ID " + storyId + " not found.");
		}
		return story;
	}
}
