package com.paulganly.staircase;

public class Staircase {

	private Long id;
	private String firstName;
	private String lastName;
	private String description;

	private Long version;

	private Staircase() {}

	public Staircase(String firstName, String lastName, String description) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
	}
}