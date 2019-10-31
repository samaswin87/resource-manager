package com.resource.common.decorator;

public class HtmlEmelemnts {

	public static String hintLabel(String message) {
		return "<a data-toggle=\"popover\" data-placement=\"top\" title=\"\" data-content=\"" + message
				+ "\"><span class=\"fa fa-question-circle hint_icon\"></span></a>";
	}
}
