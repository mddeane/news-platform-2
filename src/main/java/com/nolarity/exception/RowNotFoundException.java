package com.nolarity.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RowNotFoundException extends RuntimeException {
	public RowNotFoundException(String message) {
		super(message);
	}
}
