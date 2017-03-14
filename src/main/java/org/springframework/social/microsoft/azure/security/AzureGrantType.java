package org.springframework.social.microsoft.azure.security;

public enum AzureGrantType {
	CLIENT_CREDENTIALS("client_credentials"),
	JWT_TOKEN("jwt_token"),
	PASSWORD("password");

	public static String CLIENT_CREDENTIALS_VALUE = "client_credentials";

	private String value;

	AzureGrantType(String value){
		this.value = value;
	}

	public String asString(){
		return value;
	}
}
