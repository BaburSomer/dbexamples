package com.bilgeadam.course04.lesson47.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Telephone {
	private long number;
	private String type;
}
