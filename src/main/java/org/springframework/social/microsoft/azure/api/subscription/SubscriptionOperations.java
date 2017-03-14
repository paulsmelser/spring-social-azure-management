package org.springframework.social.microsoft.azure.api.subscription;

import org.springframework.http.ResponseEntity;

public interface SubscriptionOperations {

	ResponseEntity<SubscriptionListResult> list();
	ResponseEntity<Subscription> get(String subscriptionId);
}
