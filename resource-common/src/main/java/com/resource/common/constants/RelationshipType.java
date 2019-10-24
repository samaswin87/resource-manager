package com.resource.common.constants;

import java.util.Arrays;
import java.util.List;

public enum RelationshipType {

	SPOUSE(1, "Spouse"),
	CHILD(2, "Child"),
	MOTHER(3, "Mother"),
	FATHER(4, "Father"),
	GRANDMOTHER(5, "Grandmother"),
	GRANDFATHER(6, "Grandfather"),
	BROTHER(7, "Brother"),
	SISTER(8, "Sister"),
	PARTNER(9, "Partner"),
	FRIEND(10, "Friend"),
	HUSBAND(11, "Husband"),
	WIFE(12, "Wife"),
	SELF(13, "Self"),
	ELDER_BROTHER(14, "Elder Brother"),
	YOUNGER_BROTHER(15, "Younger Brother"),
	ELDER_SISTER(16, "Elder Sister"),
	YOUNGER_SISTER(17, "Younger Sister"),
	SON(18, "Son"),
	OLDEST_SON(19, "Oldest Son"),
	YOUNGER_SON(20, "Younger Son"),
	DAUGHTER(21, "Daughter"),
	OLDEST_DAUGHTER(22, "Oldest Daughter"),
	YOUNGER_DAUGHTER(23, "Younger Daughter"),
	MOTHER_IN_LAW(24, "Mother In Law"),
	FATHER_IN_LAW(25, "Father In Law"),
	NIECE(26, "Niece"),
	NEPHEW(27, "Nephew"),
	MALE_COUSIN(28, "Male Cousin"),
	FEMALE_COUSIN(29, "Female Cousin"),
	SECOND_DAUGHTER(30, "Second Daughter"),
	SECOND_SON(31, "Second Son"),
	SIBLING(32, "Sibling"),
	THIRD_SON(33, "Third Son"),
	THIRD_DAUGHTER(34, "Third Daughter"),
	ADOPTED_SON(35, "Adopted Son"),
	ADOPTED_DAUGHTER(36, "Adopted Daughter"),
	ADOPTED_CHILD(37, "Adopted Child"),
	UNCLE_AUNT(38, "Uncle Aunt"),
	OLDER_UNCLE(39, "Older Uncle"),
	YOUNGER_UNCLE(40, "Younger Uncle"),
	OLDER_AUNT(41, "Older Aunt"),
	YOUNGER_AUNT(42, "Younger Aunt"),
	UNCLE(43, "Uncle"),
	AUNT(44, "Aunt"),
	SON_IN_LAW(45, "Son In Law"),
	DAUGHTER_IN_LAW(46, "Daughter In Law"),
	OLDER_BROTHER_IN_LAW(47, "Older Brother In Law"),
	BROTHER_IN_LAW(48, "Brother In Law"),
	OLDER_SISTER_IN_LAW(49, "Older Sister In Law"),
	SISTER_IN_LAW(50, "Sister In Law"),
	FOURTH_SON(51, "Fourth Son"),
	FIFTH_SON(52, "Fifth Son"),
	FOURTH_DAUGHTER(53, "Fourth Daughter"),
	FIFTH_DAUGHTER(54, "Fifth Daughter"),
	OTHER(99, "Other");

	private Integer id;
	private String name;

	RelationshipType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Integer getId() {
		return this.id;
	}

	public static RelationshipType getRelationship(Integer id) {
		for (RelationshipType relation : RelationshipType.values()) {
			if (relation.getId() == id) {
				return relation;
			}
		}
		return RelationshipType.OTHER;
	}
	
	public static List<RelationshipType> getRelationshipTypes() {
   	 	return Arrays.asList(RelationshipType.values());
    }
}
