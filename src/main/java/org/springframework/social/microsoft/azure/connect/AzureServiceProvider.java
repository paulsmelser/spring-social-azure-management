package org.springframework.social.microsoft.azure.connect;

import static org.springframework.social.microsoft.azure.api.uri.UriProvider.DEFAULT_URL_PROVIDER;

import org.springframework.retry.support.RetryTemplate;
import org.springframework.social.microsoft.azure.Azure;
import org.springframework.social.microsoft.azure.api.AzureTemplate;
import org.springframework.social.microsoft.azure.api.uri.UriProvider;
import org.springframework.social.microsoft.azure.security.AbstractAzureADServiceProvider;
import org.springframework.social.microsoft.azure.security.AzureADAuthTemplate;

public class AzureServiceProvider extends AbstractAzureADServiceProvider<Azure> {

	private final static String DEFAULT_API_VERSION = "2016-09-01";
	private final String apiVersion;
	private RetryTemplate retryTemplate;
	private final UriProvider uriProvider;

	public AzureServiceProvider(String clientId, String clientSecret, String domain){
		super(new AzureADAuthTemplate(clientId, clientSecret, domain, DEFAULT_URL_PROVIDER));
		apiVersion = DEFAULT_API_VERSION;
		this.uriProvider = DEFAULT_URL_PROVIDER;
	}

	public AzureServiceProvider(String clientId, String clientSecret, String domain, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(clientId, clientSecret, domain, authority, resourceUrl, partnerServiceApiRoot));
		apiVersion = DEFAULT_API_VERSION;
		uriProvider = UriProvider.builder().authority(authority).azureServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
	}

	public AzureServiceProvider(String clientId, String clientSecret, String domain, RetryTemplate retryTemplate, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(clientId, clientSecret, domain, authority, resourceUrl, partnerServiceApiRoot));
		apiVersion = DEFAULT_API_VERSION;
		uriProvider = UriProvider.builder().authority(authority).azureServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
		this.retryTemplate = retryTemplate;
	}

	public AzureServiceProvider(String clientId, String clientSecret, String domain, RetryTemplate retryTemplate){
		super(new AzureADAuthTemplate(clientId, clientSecret, domain));
		apiVersion = DEFAULT_API_VERSION;
		uriProvider = DEFAULT_URL_PROVIDER;
		this.retryTemplate = retryTemplate;
	}


	public AzureServiceProvider(String clientId, String clientSecret, String domain, String apiVersion, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(clientId, clientSecret, domain, authority, resourceUrl, partnerServiceApiRoot));
		uriProvider = UriProvider.builder().authority(authority).azureServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
		this.apiVersion = apiVersion;
	}

	public AzureServiceProvider(String clientId, String clientSecret, String domain, String apiVersion, RetryTemplate retryTemplate, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(clientId, clientSecret, domain, authority, resourceUrl, partnerServiceApiRoot));
		uriProvider = UriProvider.builder().authority(authority).azureServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
		this.apiVersion = apiVersion;
		this.retryTemplate = retryTemplate;
	}

	@Override
	public Azure getApi(String accessToken) {
		return new AzureTemplate(retryTemplate, uriProvider, accessToken, apiVersion);
	}
}
