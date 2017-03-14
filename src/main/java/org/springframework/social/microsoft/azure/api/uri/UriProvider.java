package org.springframework.social.microsoft.azure.api.uri;

import org.springframework.web.util.UriComponentsBuilder;

public class UriProvider {
	public static final UriProvider DEFAULT_URL_PROVIDER = builder()
			.authority("https://login.windows.net")
			.resourceUrl("https://management.azure.com/")
			.azureServiceApiRoot("https://management.azure.com")
			.build();
	private String authority;
	private String resourceUrl;
	private String azureServiceApiRoot;

	private UriProvider(String authority, String resourceUrl, String azureServiceApiRoot) {
		this.authority = authority;
		this.resourceUrl = resourceUrl;
		this.azureServiceApiRoot = azureServiceApiRoot;
	}

	private UriProvider() {}

	public String buildSubscriptionUri(){
		return azureUriBuilder()
				.pathSegment("subscriptions")
				.build()
				.toString();
	}

	public String buildAzureOAuth2Uri(String tenantId){
		return UriComponentsBuilder.fromUriString(authority)
				.pathSegment(tenantId)
				.pathSegment("oauth2")
				.pathSegment("token")
				.build()
				.toString();
	}

	public  String getResourceUri(){
		return resourceUrl;
	}

	public  String getAzureServiceApiRoot(){
		return azureServiceApiRoot;
	}

	public String getAuthority(){
		return authority;
	}

	public  String buildPartnerCenterTokenUri(){
		return UriComponentsBuilder.fromUriString(azureServiceApiRoot)
				.pathSegment("generatetoken")
				.build().toString();
	}

	public  UriComponentsBuilder azureUriBuilder(){
		return UriComponentsBuilder.fromUriString(azureServiceApiRoot);
	}


	public static UriProviderBuilder builder(){
		return new UriProviderBuilder();
	}


	public static class UriProviderBuilder{
		private String authority;
		private String resourceUrl;
		private String azureServiceApiRoot;

		public UriProviderBuilder authority(String authority) {
			this.authority = authority;
			return this;
		}

		public UriProviderBuilder resourceUrl(String resourceUrl) {
			this.resourceUrl = resourceUrl;
			return this;
		}

		public UriProviderBuilder azureServiceApiRoot(String partnerServiceApiRoot) {
			this.azureServiceApiRoot = partnerServiceApiRoot;
			return this;
		}

		public UriProvider build(){
			return new UriProvider(authority, resourceUrl, azureServiceApiRoot);
		}
	}
}
