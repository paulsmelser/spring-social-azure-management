package org.springframework.social.microsoft.azure.security;

import org.springframework.social.oauth2.AccessGrant;

public class AzureAccessGrant extends AccessGrant{
	private String id_token;

	public AzureAccessGrant(String accessToken, String scope, String refreshToken, String id_token, Long expiresIn) {
		super(accessToken, scope, refreshToken, expiresIn);
		this.id_token = id_token;
	}

	public String getId_token() {
		return id_token;
	}

	public AzureAccessGrant setId_token(String id_token) {
		this.id_token = id_token;
		return this;
	}
}
