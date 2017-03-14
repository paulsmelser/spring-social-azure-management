package org.springframework.social.microsoft.azure.api.subscription;

import java.util.ArrayList;
import java.util.List;

public class Subscription {
	private String id;
	private String subscriptionId;
	private String tenantId;
	private String displayName;
	private SubscriptionState state;
	private SubscriptionPolicies subscriptionPolicies;
	private List<AuthorizationSource> authorizationSource;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public SubscriptionState getState() {
		return state;
	}

	public void setState(SubscriptionState state) {
		this.state = state;
	}

	public SubscriptionPolicies getSubscriptionPolicies() {
		return subscriptionPolicies;
	}

	public void setSubscriptionPolicies(SubscriptionPolicies subscriptionPolicies) {
		this.subscriptionPolicies = subscriptionPolicies;
	}

	public List<AuthorizationSource> getAuthorizationSource() {
		return authorizationSource;
	}

	public void setAuthorizationSource(String authorizationSource) {
		List<AuthorizationSource> authorizationSources = new ArrayList<>();
		for (String authSource : authorizationSource.split(", ")) {
			authorizationSources.add(AuthorizationSource.fromValue(authSource));
		}
		this.authorizationSource = authorizationSources;
	}
}
