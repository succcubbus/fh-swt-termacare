package de.adesso.termacare.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Abstract Entity for a person including title, gender, givenName, familyName
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@MappedSuperclass
abstract class Person {
    @Column(name = "title")
    protected String title;
    @Column(name = "gender")
    protected Gender gender;
    @Column(name = "givenNameLabel")
    protected String givenName;
    @Column(name = "familyNameLabel")
    protected String familyName;

	public String getName(){
		return familyName + ", " + givenName;
	}
}
