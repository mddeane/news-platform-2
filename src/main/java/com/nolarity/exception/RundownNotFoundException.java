package com.nolarity.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RundownNotFoundException extends RuntimeException {

	public RundownNotFoundException(String message) {
		super(message);
	}
	
}
