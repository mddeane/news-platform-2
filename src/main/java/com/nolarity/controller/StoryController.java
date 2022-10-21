package com.nolarity.controller;

import java.util.Optional;
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

import com.nolarity.model.Story;
import com.nolarity.service.StoryService;

@RestController
@RequestMapping("stories")
@CrossOrigin(origins="*", allowedHeaders="*")
public class StoryController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StoryService storyServ;
	
	@GetMapping
	public ResponseEntity<Set<Story>> getAllStories() {
		return ResponseEntity.ok(storyServ.getAllStories());
	}
	
	@PostMapping("/add")
	public ResponseEntity<Story> addStory(@Valid @RequestBody Story story) {
		ResponseEntity<Story> addedStory = ResponseEntity.ok(storyServ.add(story));
		log.info("Story ID " + addedStory.getBody().getStoryId() + " added at " + addedStory.getBody().getStoryCreatedDate() + " by " + addedStory.getBody().getStoryCreatedBy());
		return addedStory;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Story> findStoryById(@PathVariable("id") long storyId) {
		Story story = storyServ.getStoryById(storyId);
		return ResponseEntity.ok(story);
	}
	
	@PutMapping("/modify")
	public ResponseEntity<Story> modifyStory(@Valid @RequestBody Story story) {
		return ResponseEntity.ok(storyServ.modifyStory(story));
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteStory(@PathVariable("id") long storyId) {
		storyServ.deleteStory(storyId);
	}
}
