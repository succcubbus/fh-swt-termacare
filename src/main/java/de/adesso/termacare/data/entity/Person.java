package de.adesso.termacare.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
abstract class Person{
	@Column(name = "title")
	private String title;
	@Column(name = "gender")
	private Gender gender;
	@Column(name = "givenNameLabel")
	private String givenName;
	@Column(name = "familyNameLabel")
	private String familyName;
}
