package com.nolarity.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StoryNotFoundException extends RuntimeException {

	public StoryNotFoundException(String message) {
		super(message);
	}
	
}
