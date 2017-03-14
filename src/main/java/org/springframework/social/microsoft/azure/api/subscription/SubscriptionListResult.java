package org.springframework.social.microsoft.azure.api.subscription;

import java.util.List;

public class SubscriptionListResult {
	private List<Subscription> value;
	private String nextLink;

	public List<Subscription> getValue() {
		return value;
	}

	public void setValue(List<Subscription> value) {
		this.value = value;
	}

	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}
}
