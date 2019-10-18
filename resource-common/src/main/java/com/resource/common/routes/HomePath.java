package com.resource.common.routes;

public enum HomePath {

	home_show("/home");
	
	HomePath(String path) {
		this.path = path;
	}

	private String path;

	private static final String URL_REGEX = "/\\{(.*?)\\}";

	public String path() {
		return path;
	}

	public String partial() {
		return path.replaceAll(URL_REGEX, "").substring(1);
	}

	public String redirect() {
		return "redirect:" + path;
	}
}
