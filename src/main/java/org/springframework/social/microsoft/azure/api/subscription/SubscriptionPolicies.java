package org.springframework.social.microsoft.azure.api.subscription;

public class SubscriptionPolicies {
	private String locationPlacementId;
	private String quotaId;
	private SpendingLimit spendingLimit;

	public String getLocationPlacementId() {
		return locationPlacementId;
	}

	public void setLocationPlacementId(String locationPlacementId) {
		this.locationPlacementId = locationPlacementId;
	}

	public String getQuotaId() {
		return quotaId;
	}

	public void setQuotaId(String quotaId) {
		this.quotaId = quotaId;
	}

	public SpendingLimit getSpendingLimit() {
		return spendingLimit;
	}

	public void setSpendingLimit(SpendingLimit spendingLimit) {
		this.spendingLimit = spendingLimit;
	}
}
