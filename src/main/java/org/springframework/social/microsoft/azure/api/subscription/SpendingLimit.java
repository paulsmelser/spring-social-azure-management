package org.springframework.social.microsoft.azure.api.subscription;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SpendingLimit {
	ON("On"), OFF("Off"), CURRENT_PERIOD_OFF("CurrentPeriodOff");

	private String jsonValue;

	SpendingLimit(String json){
		this.jsonValue = json;
	}

	@JsonValue
	public String jsonValue() {
		return this.jsonValue;
	}
}
