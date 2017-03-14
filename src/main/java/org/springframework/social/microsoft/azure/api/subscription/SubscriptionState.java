package org.springframework.social.microsoft.azure.api.subscription;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SubscriptionState {
	ENABLED("Enabled"), WARNED("Warned"), PAST_DUE("PastDue"), DISABLED("Disabled"), DELETED("Deleted");

	private String jsonValue;

	SubscriptionState(String json){
		this.jsonValue = json;
	}

	@JsonValue
	public String jsonValue() {
		return this.jsonValue;
	}
}
