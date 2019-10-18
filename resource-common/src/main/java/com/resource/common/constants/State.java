package com.resource.common.constants;

import java.util.Arrays;
import java.util.List;

public enum State {
	
	NOT_AVAILABLE(0, "Not available"),
	OTHERS(40, "OTHERS"),
	
	// INIDAN STATES
	ANDHRA_PRADESH(1,"Andhra Pradesh"),
	ARUNACHAL_PRADESH(2,"Arunachal Pradesh"),
	ASSAM(3,"Assam"),
	BIHAR(4,"Bihar"),
	CHHATTISGARH(5,"Chhattisgarh"),
	GOA(6,"Goa"),
	GUJARAT(7,"Gujarat"),
	HARYANA(8,"Haryana"),
	HIMACHAL_PRADESH(9,"Himachal Pradesh"),
	JAMMU_AND_KASHMIR(10,"Jammu and Kashmir"),
	JHARKHAND(11,"Jharkhand"),
	KARNATAKA(12,"Karnataka"),
	KERALA(13,"Kerala"),
	MADHYA_PRADESH(14,"Madhya Pradesh"),
	MAHARASHTRA(15,"Maharashtra"),
	MANIPUR(16,"Manipur"),
	MEGHALAYA(17,"Meghalaya"),
	MIZORAM(18,"Mizoram"),
	NAGALAND(19,"Nagaland"),
	ODISHA(20,"Odisha"),
	PUNJAB(21,"Punjab"),
	RAJASTHAN(22,"Rajasthan"),
	SIKKIM(23,"Sikkim"),
	TAMIL_NADU(24,"Tamil Nadu"),
	TELANGANA(25,"Telangana"),
	TRIPURA(26,"Tripura"),
	UTTAR_PRADESH(27,"Uttar Pradesh"),
	UTTARAKHAND(28,"Uttarakhand"),
	WEST_BENGAL(29,"West Bengal");

	private Integer id;
	private String name;

	State(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Integer getId() {
		return this.id;
	}

	public static State getState(Integer id) {
		for (State state : State.values()) {
			if (state.getId() == id) {
				return state;
			}
		}
		return null;
	}
	
	public static List<State> geStates() {
   	 	return Arrays.asList(State.values());
    }
}
