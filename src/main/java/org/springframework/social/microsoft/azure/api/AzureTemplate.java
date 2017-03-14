package org.springframework.social.microsoft.azure.api;

import static java.util.Objects.nonNull;

import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.social.microsoft.azure.Azure;
import org.springframework.social.microsoft.azure.api.subscription.SubscriptionOperations;
import org.springframework.social.microsoft.azure.api.subscription.impl.SubscriptionTemplate;
import org.springframework.social.microsoft.azure.api.uri.UriProvider;
import org.springframework.social.microsoft.azure.connect.ApiVersionParameterRequestInterceptor;
import org.springframework.social.microsoft.azure.http.client.RestResource;
import org.springframework.social.microsoft.azure.http.client.RetryRestResource;
import org.springframework.social.microsoft.azure.http.client.retry.RetryService;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.web.client.RestTemplate;

public class AzureTemplate extends AbstractOAuth2ApiBinding implements Azure{
	private final SubscriptionOperations subscriptionOperations;

	public AzureTemplate(RetryTemplate retryTemplate, UriProvider uriProvider, String accessToken, String version){
		super(accessToken);
		addVersionInterceptor(version);
		subscriptionOperations = new SubscriptionTemplate(createRestResource(uriProvider.buildSubscriptionUri(), retryTemplate), isAuthorized());
	}

	private RestResource createRestResource(String baseUri, RetryTemplate retryTemplate){
		if(nonNull(retryTemplate)){
			return new RetryRestResource(getRestTemplate(), baseUri, new RetryService(retryTemplate));
		}
		return new RestResource(getRestTemplate(), baseUri);
	}

	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		BufferingClientHttpRequestFactory requestFactory = new BufferingClientHttpRequestFactory(factory);
		restTemplate.setRequestFactory(requestFactory);
	}

	@Override
	public SubscriptionOperations getSubscriptionOperations() {
		return subscriptionOperations;
	}

	private void addVersionInterceptor(String apiVersion) {
		getRestTemplate().getInterceptors().add(new ApiVersionParameterRequestInterceptor(apiVersion));
	}
}
