package de.adesso.termacare.gui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class DtoPatient implements DtoData {
	private long id;

	private String gender;
	private String title;
	private String givenName;
	private String familyName;

	private String livingPostcode;
	private String livingAddress;

	private String billingPostcode;
	private String billingAddress;
}