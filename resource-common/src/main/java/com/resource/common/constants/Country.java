package com.resource.common.constants;

import java.util.Arrays;
import java.util.List;

public enum Country {

	INDIA(1, "India"), SRILANKA(2, "Sri Lanka"), PAKISTHAN(3, "Pakistan"), BANGLADESH(4, "Bangladesh"),
	OTHERS(99, "Others");

	private Integer id;
	private String name;

	Country(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Integer getId() {
		return this.id;
	}

	public static Country getCountry(Integer id) {
		for (Country country : Country.values()) {
			if (country.getId() == id) {
				return country;
			}
		}
		return Country.INDIA;
	}

	public static List<Country> getCountries() {
   	 	return Arrays.asList(Country.values());
    }
}
