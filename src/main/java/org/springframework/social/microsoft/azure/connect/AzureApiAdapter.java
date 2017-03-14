package org.springframework.social.microsoft.azure.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.microsoft.azure.Azure;

public class AzureApiAdapter implements ApiAdapter<Azure> {
	@Override
	public boolean test(Azure api) {
		return false;
	}

	@Override
	public void setConnectionValues(Azure api, ConnectionValues values) {

	}

	@Override
	public UserProfile fetchUserProfile(Azure api) {
		return null;
	}

	@Override
	public void updateStatus(Azure api, String message) {

	}
}
