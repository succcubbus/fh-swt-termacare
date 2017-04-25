package de.adesso.termacare.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
public class Address implements EntityInterface{
	@Id @GeneratedValue
	private long id;

	@Column(name = "postcode")
	private String postcode;
	@Column(name = "departure")
	private String departure;
	@Column(name = "address")
	private String address;
	@Column(name = "homeNumber")
	private String number;
}