package org.springframework.social.microsoft.azure.connect;

import org.springframework.retry.support.RetryTemplate;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.microsoft.azure.Azure;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;

public class AzureConnectionFactory extends BaseAzureConnectionFactory {

	public AzureConnectionFactory(String clientId, String clientSecret, String domain){
		this(Azure.PROVIDER_ID, new AzureServiceProvider(clientId, clientSecret, domain), new AzureApiAdapter());
	}

	public AzureConnectionFactory(String clientId, String clientSecret, String domain, String authority, String resourceUrl, String azureServiceApiRoot){
		this(Azure.PROVIDER_ID, new AzureServiceProvider(clientId, clientSecret, domain, authority, resourceUrl, azureServiceApiRoot), new AzureApiAdapter());
	}

	public AzureConnectionFactory(String clientId, String clientSecret, String domain, RetryTemplate retryTemplate){
		this(Azure.PROVIDER_ID, new AzureServiceProvider(clientId, clientSecret, domain, retryTemplate), new AzureApiAdapter());
	}

	public AzureConnectionFactory(String clientId, String clientSecret, String domain, RetryTemplate retryTemplate, String authority, String resourceUrl, String azureServiceApiRoot){
		this(Azure.PROVIDER_ID, new AzureServiceProvider(clientId, clientSecret, domain, retryTemplate, authority, resourceUrl, azureServiceApiRoot), new AzureApiAdapter());
	}

	public AzureConnectionFactory(String clientId, String clientSecret, String domain, String apiVersion, String authority, String resourceUrl, String azureServiceApiRoot){
		this(Azure.PROVIDER_ID, new AzureServiceProvider(clientId, clientSecret, domain, apiVersion, authority, resourceUrl, azureServiceApiRoot), new AzureApiAdapter());
	}

	public AzureConnectionFactory(String clientId, String clientSecret, String domain, String apiVersion, RetryTemplate retryTemplate, String authority, String resourceUrl, String azureServiceApiRoot){
		this(Azure.PROVIDER_ID, new AzureServiceProvider(clientId, clientSecret, domain, apiVersion, retryTemplate, authority, resourceUrl, azureServiceApiRoot), new AzureApiAdapter());
	}

	private AzureConnectionFactory(String providerId, AzureServiceProvider serviceProvider, ApiAdapter<Azure> apiAdapter) {
		super(providerId, serviceProvider, apiAdapter);
	}

	public AzureConnection createConnection(){
		AccessGrant accessGrant = getAuthOperations().exchangeForAccess();
		return (AzureConnection) createConnection(accessGrant);
	}

	public AzureConnection createConnection(String username, String password){
		AccessGrant accessGrant = getAuthOperations().exchangeCredentialsForAccess(username, password, new OAuth2Parameters());
		return (AzureConnection) createConnection(accessGrant);
	}


	@Override
	public Connection<Azure> createConnection(AccessGrant accessGrant) {
		return super.createConnection(accessGrant);
	}

	@Override
	public Connection<Azure> createConnection(ConnectionData data) {
		return super.createConnection(data);
	}
}
