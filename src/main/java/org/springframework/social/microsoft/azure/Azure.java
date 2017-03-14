package org.springframework.social.microsoft.azure;

import org.springframework.social.ApiBinding;
import org.springframework.social.microsoft.azure.api.subscription.SubscriptionOperations;

public interface Azure extends ApiBinding {
	String PROVIDER_ID = "azure";

	SubscriptionOperations getSubscriptionOperations();
}
