package org.springframework.social.microsoft.azure.api.subscription;

import java.util.List;

public class LocationListResult {
	private List<Location> value;

	public List<Location> getValue() {
		return value;
	}

	public void setValue(List<Location> value) {
		this.value = value;
	}
}
