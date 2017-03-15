package org.springframework.social.microsoft.azure.api.subscription.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.social.microsoft.azure.Azure;
import org.springframework.social.microsoft.azure.api.AbstractTemplate;
import org.springframework.social.microsoft.azure.api.subscription.LocationListResult;
import org.springframework.social.microsoft.azure.api.subscription.Subscription;
import org.springframework.social.microsoft.azure.api.subscription.SubscriptionListResult;
import org.springframework.social.microsoft.azure.api.subscription.SubscriptionOperations;
import org.springframework.social.microsoft.azure.http.client.RestResource;

public class SubscriptionTemplate extends AbstractTemplate implements SubscriptionOperations{
	public static final String LOCATIONS = "locations";
	private final RestResource restResource;
	public SubscriptionTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	protected String getProviderId() {
		return Azure.PROVIDER_ID;
	}

	@Override
	public ResponseEntity<SubscriptionListResult> list() {
		return restResource.request().get(SubscriptionListResult.class);
	}

	@Override
	public ResponseEntity<Subscription> get(String subscriptionId) {
		return restResource.request()
				.pathSegment(subscriptionId)
				.get(Subscription.class);
	}

	@Override
	public ResponseEntity<LocationListResult> locations(String subscriptionId) {
		return restResource.request()
				.pathSegment(subscriptionId, LOCATIONS)
				.get(LocationListResult.class);
	}
}
