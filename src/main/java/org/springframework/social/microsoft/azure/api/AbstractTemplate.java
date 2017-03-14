package org.springframework.social.microsoft.azure.api;

import org.springframework.social.MissingAuthorizationException;

public abstract class AbstractTemplate {
	private boolean isAuthorized;

	protected AbstractTemplate(boolean isAuthorized){
		this.isAuthorized = isAuthorized;
		checkAuthorization();
	}
	private void checkAuthorization(){
		if (!isAuthorized) {
			throw new MissingAuthorizationException(getProviderId());
		}
	}

	protected abstract String getProviderId();
}

