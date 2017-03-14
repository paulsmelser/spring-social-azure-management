package org.springframework.social.microsoft.azure.api.subscription;

import static java.lang.String.format;
import static java.util.Arrays.stream;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AuthorizationSource {
	LEGACY("Legacy"), ROLE_BASED("RoleBased"), BYPASSED("Bypassed"), DIRECT("Direct"), MANAGEMENT("Management");

	private String jsonValue;

	AuthorizationSource(String json){
		this.jsonValue = json;
	}

	@JsonValue
	public String jsonValue() {
		return this.jsonValue;
	}

	public static AuthorizationSource fromValue(String value){
		return stream(AuthorizationSource.values())
				.filter(authorizationSource -> authorizationSource.jsonValue().equals(value))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(format("Could not find AuthorizationSource for string value=%s", value)));
	}
}
